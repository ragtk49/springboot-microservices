package com.venkatagollapudi.orderservice.service;

import com.venkatagollapudi.orderservice.dto.InventoryResponse;
import com.venkatagollapudi.orderservice.dto.OrderLineItemsDto;
import com.venkatagollapudi.orderservice.dto.OrderRequest;
import com.venkatagollapudi.orderservice.event.OrderPlacedEvent;
import com.venkatagollapudi.orderservice.model.Order;
import com.venkatagollapudi.orderservice.model.OrderLineItems;
import com.venkatagollapudi.orderservice.repository.OrderRepository;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service // Spring annotation to indicate that this is a service class
@RequiredArgsConstructor // Lombok annotation to generate a constructor with all final fields
@Transactional // Spring annotation to indicate that this service class is transactional
@Slf4j // Lombok annotation to generate a logger field
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    private final Tracer tracer;

    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;
    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::maptoDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes =  order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        log.info("Calling inventory-service");

        Span inventoryServiceLookup =  tracer.nextSpan().name("InventoryServiceLookup");

        try (Tracer.SpanInScope spanInScope = tracer.withSpan(inventoryServiceLookup.start())) {

            //Call Inventory Service , and place order if products are in stock
            InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                    .uri("http://inventory-service/api/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();

            boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);

            if(allProductsInStock){
                orderRepository.save(order);
                kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
                return "Order Placed Successfully";
            } else{
                throw new IllegalArgumentException("Product is out of stock");
            }
        }
        finally{
            inventoryServiceLookup.end();
        }
    }

    private OrderLineItems maptoDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }


}

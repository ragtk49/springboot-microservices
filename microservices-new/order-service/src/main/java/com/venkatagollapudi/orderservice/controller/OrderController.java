package com.venkatagollapudi.orderservice.controller;

import com.venkatagollapudi.orderservice.dto.OrderRequest;
import com.venkatagollapudi.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor // Lombok annotation to generate a constructor with all final fields
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
//    @TimeLimiter(name="inventory")
//    @Retry(name="inventory")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        return orderService.placeOrder(orderRequest);
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(() -> "Order Service is taking too long to respond or is down. Please try again later");
    }
}

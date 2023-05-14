package com.venkatagollapudi.inventoryservice.service;

import com.venkatagollapudi.inventoryservice.dto.InventoryResponse;
import com.venkatagollapudi.inventoryservice.model.Inventory;
import com.venkatagollapudi.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // Spring annotation to indicate that this is a service class
@RequiredArgsConstructor // Lombok annotation to generate a constructor with all final fields
@Slf4j // Lombok annotation to generate a logger field
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true) // Spring annotation to indicate that this service class is transactional
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("Wait started");
        Thread.sleep(10000);
        log.info("Wait ended");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                )
                .toList();
    }
}

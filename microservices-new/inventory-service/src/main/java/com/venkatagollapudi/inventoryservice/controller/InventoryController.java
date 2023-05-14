package com.venkatagollapudi.inventoryservice.controller;

import com.venkatagollapudi.inventoryservice.dto.InventoryResponse;
import com.venkatagollapudi.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This annotation is a combination of @Controller and @ResponseBody annotations
@RequestMapping("/api/inventory") // This annotation is used to map web requests onto specific handler classes and/or handler methods
@RequiredArgsConstructor // Lombok annotation to generate a constructor with all final fields
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping// This annotation is used to map HTTP GET requests onto specific handler methods
    @ResponseStatus(HttpStatus.OK) // This annotation is used to set the HTTP status code in the response
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){

        return inventoryService.isInStock(skuCode);
    }

}

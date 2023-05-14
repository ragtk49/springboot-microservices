package com.venkatagollapudi.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters, setters, constructors, toString, hash, equals, etc.
@AllArgsConstructor // Lombok annotation to generate a constructor with all arguments
@NoArgsConstructor // Lombok annotation to generate a constructor with no arguments
@Builder // Lombok annotation to generate a builder for the class
public class InventoryResponse {

    private String skuCode;
    private boolean isInStock;

}

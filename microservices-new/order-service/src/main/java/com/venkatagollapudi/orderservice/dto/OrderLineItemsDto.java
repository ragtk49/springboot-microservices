package com.venkatagollapudi.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data // Lombok annotation to generate getters, setters, constructors, toString, hash, equals, etc.
@AllArgsConstructor // Lombok annotation to generate a constructor with all arguments
@NoArgsConstructor // Lombok annotation to generate a constructor with no arguments
public class OrderLineItemsDto {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

}

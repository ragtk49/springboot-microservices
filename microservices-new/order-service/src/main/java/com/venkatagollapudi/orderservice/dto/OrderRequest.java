package com.venkatagollapudi.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // Lombok annotation to generate getters, setters, constructors, toString, hash, equals, etc.
@AllArgsConstructor // Lombok annotation to generate a constructor with all arguments
@NoArgsConstructor // Lombok annotation to generate a constructor with no arguments
public class OrderRequest {

    private List<OrderLineItemsDto> orderLineItemsDtoList;
}

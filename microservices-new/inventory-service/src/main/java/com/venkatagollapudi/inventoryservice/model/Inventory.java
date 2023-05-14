package com.venkatagollapudi.inventoryservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA annotation to make this object ready for storage in a JPA-based data store
@Table(name = "t_inventory")    // Table name in the database
@Getter // Lombok annotation to create getters for all fields
@Setter // Lombok annotation to create setters for all fields
@AllArgsConstructor // Lombok annotation to create a constructor with all arguments
@NoArgsConstructor // Lombok annotation to create a constructor with no arguments
public class Inventory {

    @Id // JPA annotation for the primary key column of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA annotation for the primary key column of the entity
    private Long id;
    private String skuCode;
    private Integer quantity;
}

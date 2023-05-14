package com.venkatagollapudi.orderservice.repository;

import com.venkatagollapudi.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

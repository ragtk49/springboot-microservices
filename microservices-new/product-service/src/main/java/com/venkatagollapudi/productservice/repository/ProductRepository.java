package com.venkatagollapudi.productservice.repository;

import com.venkatagollapudi.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}

package com.soulrebel.springboot.app.products.models.repository;

import com.soulrebel.springboot.app.products.models.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}

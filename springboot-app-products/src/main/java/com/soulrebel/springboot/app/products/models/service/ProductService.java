package com.soulrebel.springboot.app.products.models.service;

import com.soulrebel.springboot.app.products.models.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllServ();

    Product findByIdServ(Long id);
}

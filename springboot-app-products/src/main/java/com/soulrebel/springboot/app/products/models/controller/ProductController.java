package com.soulrebel.springboot.app.products.models.controller;

import com.soulrebel.springboot.app.products.models.entity.Product;
import com.soulrebel.springboot.app.products.models.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping("/list")
    public List<Product> listProducts() {
        return productService.findAllServ();
    }

    @GetMapping("list/{id}")
    public Product detailProduct(@PathVariable Long id) {
        return productService.findByIdServ(id);
    }
}

package com.soulrebel.springboot.app.item.clients;

import com.soulrebel.springboot.app.item.models.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")

public interface ProductClientRest {

    @GetMapping("/list")
    List<Product>listProducts();

    @GetMapping("see/{id}")
    Product detailProduct(@PathVariable Long id);
}

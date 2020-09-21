package com.soulrebel.springboot.app.item.clients;

import com.soulrebel.springboot.app.item.models.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service")

public interface ProductClientRest {

    @GetMapping("/list")
    List<Product> listProducts();

    @GetMapping("see/{id}")
    Product detailProduct(@PathVariable Long id);

    @PostMapping("/create")
    public Product create(@RequestBody Product product);

    @PutMapping("/edit/{id}")
    public Product update(@RequestBody Product product, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id);
}

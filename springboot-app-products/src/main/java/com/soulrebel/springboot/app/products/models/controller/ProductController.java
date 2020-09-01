package com.soulrebel.springboot.app.products.models.controller;

import com.soulrebel.springboot.app.products.models.entity.Product;
import com.soulrebel.springboot.app.products.models.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final Environment env;

    @Value("${server.port}")
    private Integer port;

    private final ProductServiceImpl productService;

    @GetMapping("/list")
    public List<Product> listProducts() {
        return productService.findAllServ( ).stream( ).peek(product -> {
            //product.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port")))))
            product.setPort(port);
        }).collect(Collectors.toList( ));
    }

    @GetMapping("see/{id}")
    public Product detailProduct(@PathVariable Long id) {
        Product product = productService.findByIdServ(id);
        //product.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
        product.setPort(port);
        return product;
    }
}

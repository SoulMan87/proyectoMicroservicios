package com.soulrebel.springboot.app.products.models.controller;

import com.soulrebel.springboot.app.products.models.entity.Product;
import com.soulrebel.springboot.app.products.models.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Product detailProduct(@PathVariable Long id) throws Exception {
        Product product = productService.findByIdServ(id);
        //product.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("local.server.port"))));
        product.setPort(port);

        /*try {
            Thread.sleep(2000L);
        }catch (InterruptedException exception){
            exception.printStackTrace();
        }*/
        return product;
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        return productService.saveServ(product);
    }
}

package com.soulrebel.springboot.app.item.models.service;

import com.soulrebel.springboot.app.item.models.entity.Item;
import com.soulrebel.springboot.app.item.models.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service("itemServiceRestTemplate")
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final RestTemplate restClient;

    @Override
    public List<Item> findAllServ() {
        List<Product> products = Arrays.asList(Objects.requireNonNull(restClient.getForObject("http://product-service/list", Product[].class)));
        return products.stream().map(product ->
                new Item(product, 1))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findByIdServ(Long id, Integer quantity) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        Product product = restClient.getForObject("http://product-service/see/{id}", Product.class, pathVariables);
        return Optional.of(new Item(product, quantity));
    }

    @Override
    public Optional<Product> saveServ(Product product) {
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> response = restClient.exchange("http://product-service/create",
                HttpMethod.POST, body, Product.class);
        Product productResponse = response.getBody();
        return Optional.of(productResponse);
    }

    @Override
    public Optional<Product> updateServ(Product product, Long id) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        HttpEntity<Product> body = new HttpEntity<Product>(product);
        ResponseEntity<Product> response = restClient.exchange("http://product-service/edit/{id}",
                HttpMethod.PUT, body, Product.class, pathVariables);
        return Optional.of(response.getBody());
    }

    @Override
    public void deleteServ(Long id) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        restClient.delete("http://product-service/delete/{id}", pathVariables);
    }
}

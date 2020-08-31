package com.soulrebel.springboot.app.item.models.service;

import com.soulrebel.springboot.app.item.models.entity.Item;
import com.soulrebel.springboot.app.item.models.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service("itemService")
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final RestTemplate restClient;

    @Override
    public List<Item> findAllServ() {
        List<Product> products = Arrays.asList(Objects.requireNonNull(restClient.getForObject("http://localhost:8001/list", Product[].class)));
        return products.stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findByIdServ(Long id, Integer quantity) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        Product product = restClient.getForObject("http://localhost:8001/see/{id}", Product.class, pathVariables);
        return Optional.of(new Item(product, quantity));
    }
}

package com.soulrebel.springboot.app.item.models.service;

import com.soulrebel.springboot.app.item.models.entity.Item;
import com.soulrebel.springboot.app.item.models.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> findAllServ();

    Optional<Item> findByIdServ(Long id, Integer quantity);

    Optional<Product> saveServ(Product product);

    Optional<Product> updateServ(Product product, Long id);

    void deleteServ(Long id);
}

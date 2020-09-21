package com.soulrebel.springboot.app.item.models.service;

import com.soulrebel.springboot.app.item.clients.ProductClientRest;
import com.soulrebel.springboot.app.item.models.entity.Item;
import com.soulrebel.springboot.app.item.models.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("itemServiceFeign")
@Primary
public class ItemServiceFeign implements ItemService {

    private final ProductClientRest productClientRest;

    @Override
    public List<Item> findAllServ() {

        return productClientRest.listProducts().stream()
                .map(product ->
                        new Item(product, 1))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findByIdServ(Long id, Integer quantity) {
        return Optional.of(new Item(productClientRest.detailProduct(id), quantity));
    }

    @Override
    public Optional<Product> saveServ(Product product) {
        return Optional.of(productClientRest.create(product));
    }

    @Override
    public Optional<Product> updateServ(Product product, Long id) {
        return Optional.of(productClientRest.update(product, id));
    }

    @Override
    public void deleteServ(Long id) {
        productClientRest.delete(id);
    }
}

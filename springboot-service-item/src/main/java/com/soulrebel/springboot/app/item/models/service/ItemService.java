package com.soulrebel.springboot.app.item.models.service;

import com.soulrebel.springboot.app.item.models.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item>findAllServ();

    Optional<Item>findByIdServ(Long id, Integer quantity);
}

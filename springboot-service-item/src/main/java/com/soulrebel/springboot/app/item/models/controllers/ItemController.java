package com.soulrebel.springboot.app.item.models.controllers;

import com.soulrebel.springboot.app.item.models.entity.Item;
import com.soulrebel.springboot.app.item.models.service.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemServiceImpl itemService;

    @GetMapping("/list")
    public List<Item> itemList() {
        return itemService.findAllServ();
    }

    @GetMapping("/see/{id}/quantity/{quantity}")
    public Optional<Item> detail(@PathVariable Long id, @PathVariable Integer quantity) {
        return itemService.findByIdServ(id, quantity);
    }
}

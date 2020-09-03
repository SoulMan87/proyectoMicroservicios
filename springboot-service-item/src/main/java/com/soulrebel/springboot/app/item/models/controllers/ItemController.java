package com.soulrebel.springboot.app.item.models.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.soulrebel.springboot.app.item.models.entity.Item;
import com.soulrebel.springboot.app.item.models.entity.Product;
import com.soulrebel.springboot.app.item.models.service.ItemServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Qualifier("itemServiceFeign")
    private final ItemServiceImpl itemService;

    @Value("${configuration.text}")
    private String text;

    @GetMapping("/list")
    public List<Item> itemList() {
        return itemService.findAllServ();
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod")
    @GetMapping("/see/{id}/quantity/{quantity}")
    public Optional<Item> detail(@PathVariable Long id, @PathVariable Integer quantity) {
        return itemService.findByIdServ(id, quantity);
    }

    @SuppressWarnings("unused")
    public Optional<Item> fallbackMethod(Long id, Integer quantity) {
        Item item = new Item();
        Product product = new Product();
        item.setQuantity(quantity);
        product.setId(id);
        product.setName("Scooter");
        product.setPrice(500.00);
        item.setProduct(product);
        return Optional.of(item);
    }

    @GetMapping("/get-config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String port) {
        LOGGER.info(text);
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("text", text);
        jsonMap.put("port", port);
        return new ResponseEntity<Map<String, String>>(jsonMap, HttpStatus.OK);
    }
}

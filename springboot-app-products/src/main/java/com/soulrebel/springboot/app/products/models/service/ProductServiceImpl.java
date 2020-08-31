package com.soulrebel.springboot.app.products.models.service;

import com.soulrebel.springboot.app.products.models.entity.Product;
import com.soulrebel.springboot.app.products.models.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllServ() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findByIdServ(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}

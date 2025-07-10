package com.techlab.app.service;

import java.util.List;
import java.util.Optional;

import com.techlab.app.model.Product;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Product update(Long id, Product updated);
    boolean delete(Long id);
}
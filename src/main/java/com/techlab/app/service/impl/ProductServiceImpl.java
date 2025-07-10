package com.techlab.app.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.techlab.app.model.Product;
import com.techlab.app.repository.ProductRepository;
import com.techlab.app.service.ProductService;
import com.techlab.app.service.impl.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product updated) {
        return productRepository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setDescription(updated.getDescription());
            existing.setPrice(updated.getPrice());
            existing.setImage(updated.getImage());
            existing.setStock(updated.getStock());
            existing.setCategory(updated.getCategory());
            return productRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public boolean delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
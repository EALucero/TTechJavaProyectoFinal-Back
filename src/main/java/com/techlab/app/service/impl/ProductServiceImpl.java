package com.techlab.app.service.impl;

import com.techlab.app.model.Product;
import com.techlab.app.repository.ProductRepository;
import com.techlab.app.service.ProductService;
import com.techlab.app.dto.ProductDTO;
import com.techlab.app.dto.ProductResponseDTO;
import com.techlab.app.model.Category;
import com.techlab.app.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public Optional<ProductResponseDTO> findById(Long id) {
        return productRepository.findById(id)
                .map(this::toResponseDTO);
    }

    @Override
    public ProductResponseDTO save(@RequestBody ProductDTO dto) {
        if (dto.getCategory_id() == null) {
            throw new IllegalArgumentException("El campo categoryId no puede ser null");
        }

        Category category = categoryRepository.findById(dto.getCategory_id())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImage(dto.getImage());
        product.setStock(dto.getStock());
        product.setCategory(category);

        return toResponseDTO(productRepository.save(product));
    }

    @Override
    public ProductResponseDTO update(Long id, ProductDTO dto) {
        return productRepository.findById(id)
                .map(existing -> {
                    Category category = categoryRepository.findById(dto.getCategory_id())
                            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

                    existing.setName(dto.getName());
                    existing.setDescription(dto.getDescription());
                    existing.setPrice(dto.getPrice());
                    existing.setImage(dto.getImage());
                    existing.setStock(dto.getStock());
                    existing.setCategory(category);

                    return toResponseDTO(productRepository.save(existing));
                }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public boolean delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImage(),
                product.getStock(),
                product.getCategory().getId(),
                product.getCategory().getName());
    }
    
}
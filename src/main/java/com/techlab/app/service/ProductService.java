package com.techlab.app.service;

import com.techlab.app.dto.ProductDTO;
import com.techlab.app.dto.ProductResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponseDTO> findAll();
    Optional<ProductResponseDTO> findById(Long id);
    ProductResponseDTO save(ProductDTO dto);
    ProductResponseDTO update(Long id, ProductDTO dto);
    boolean delete(Long id);
}
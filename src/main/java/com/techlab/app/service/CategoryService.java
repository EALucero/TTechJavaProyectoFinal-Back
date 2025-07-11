package com.techlab.app.service;

import com.techlab.app.dto.CategoryDTO;
import com.techlab.app.dto.CategoryResponseDTO;
import com.techlab.app.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryResponseDTO> findAll();
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
    CategoryResponseDTO save(CategoryDTO dto);
}
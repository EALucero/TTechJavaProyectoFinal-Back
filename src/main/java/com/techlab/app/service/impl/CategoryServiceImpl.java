package com.techlab.app.service.impl;

import com.techlab.app.dto.CategoryDTO;
import com.techlab.app.dto.CategoryResponseDTO;
import com.techlab.app.model.Category;
import com.techlab.app.model.Product;
import com.techlab.app.repository.CategoryRepository;
import com.techlab.app.service.CategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public CategoryResponseDTO save(CategoryDTO dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("El campo name no puede ser null o vacío");
        }

        Category category = new Category();
        category.setName(dto.getName());

        return toResponseDTO(categoryRepository.save(category));
    }

    private CategoryResponseDTO toResponseDTO(Category category) {
        List<Long> productIds = category.getProducts().stream()
                .map(Product::getId)
                .toList();

        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                productIds);
    }
    
}
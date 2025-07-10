package com.techlab.app.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.techlab.app.model.Category;
import com.techlab.app.repository.CategoryRepository;
import com.techlab.app.service.CategoryService;
import com.techlab.app.service.impl.CategoryServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
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
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
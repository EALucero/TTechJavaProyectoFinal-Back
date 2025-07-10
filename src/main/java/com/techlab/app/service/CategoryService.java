package com.techlab.app.service;

import java.util.List;
import java.util.Optional;

import com.techlab.app.model.Category;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
    Category save(Category category);
}
package com.techlab.app.controller;

import com.techlab.app.dto.CategoryDTO;
import com.techlab.app.dto.CategoryResponseDTO;
import com.techlab.app.model.Product;
import com.techlab.app.service.CategoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.save(dto));
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryResponseDTO> getByName(@PathVariable String name) {
        return categoryService.findByName(name)
                .map(category -> new CategoryResponseDTO(
                        category.getId(),
                        category.getName(),
                        category.getProducts().stream()
                                .map(Product::getId)
                                .toList()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
}
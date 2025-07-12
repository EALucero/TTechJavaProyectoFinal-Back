package com.techlab.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private Integer stock;
    @NotNull(message = "El campo categoryId es obligatorio")
    private Long category_id;
}
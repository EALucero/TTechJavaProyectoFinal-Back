package com.techlab.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemOrderDTO {
    private Long productId;
    private Integer quantity;
}
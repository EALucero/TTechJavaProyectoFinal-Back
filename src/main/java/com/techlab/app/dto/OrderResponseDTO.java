package com.techlab.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private List<ItemOrderDTO> itemsOrder;
    private String userEmail;
    private BigDecimal totalPrice;
}

package com.techlab.app.service;

import com.techlab.app.dto.OrderDTO;
import com.techlab.app.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    List<OrderResponseDTO> findAll();
    List<OrderResponseDTO> findByUserId(Long userId);
    OrderResponseDTO save(OrderDTO order);
}
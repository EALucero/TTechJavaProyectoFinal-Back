package com.techlab.app.service;

import com.techlab.app.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    List<Order> findByUserId(Long userId);
    Order save(Order order);
}
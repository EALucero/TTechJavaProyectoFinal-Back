package com.techlab.app.service;

import java.util.List;

import com.techlab.app.model.Order;

public interface OrderService {
    List<Order> findAll();
    List<Order> findByUserId(Long userId);
    Order save(Order order);
}
package com.techlab.app.service.impl;

import com.techlab.app.model.Order;
import com.techlab.app.repository.OrderRepository;
import com.techlab.app.service.OrderService;
import com.techlab.app.service.impl.OrderServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUser_Id(userId);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
    
}
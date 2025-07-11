package com.techlab.app.controller;

import com.techlab.app.model.Order;
import com.techlab.app.service.impl.OrderServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderService.findByUserId(userId);
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return orderService.save(order);
    }
    
}
package com.techlab.app.controller;

import com.techlab.app.dto.OrderDTO;
import com.techlab.app.dto.OrderResponseDTO;
import com.techlab.app.service.OrderService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@RequestBody OrderDTO dto) {
        return ResponseEntity.ok(orderService.save(dto));
    }

    @GetMapping("/users/{userId}")
    public List<OrderResponseDTO> getOrdersByUser(@PathVariable Long userId) {
        return orderService.findByUserId(userId);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

}
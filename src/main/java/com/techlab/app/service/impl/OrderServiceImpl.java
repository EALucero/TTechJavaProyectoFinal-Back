package com.techlab.app.service.impl;

import com.techlab.app.dto.OrderDTO;
import com.techlab.app.dto.OrderResponseDTO;
import com.techlab.app.exception.InsufficientStockException;
import com.techlab.app.model.Order;
import com.techlab.app.model.Product;
import com.techlab.app.model.User;
import com.techlab.app.repository.OrderRepository;
import com.techlab.app.repository.ProductRepository;
import com.techlab.app.repository.UserRepository;
import com.techlab.app.service.OrderService;
import com.techlab.app.service.impl.OrderServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderResponseDTO save(OrderDTO dto) {
        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("El campo userId no puede ser null");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Order order = new Order();
        order.setUser(user);
        order.setItemsOrder(dto.getItemsOrder());

        BigDecimal total = dto.getItemsOrder().stream()
                .map(item -> {
                    Product product = productRepository.findById(item.getProductId())
                            .orElseThrow(() -> new IllegalArgumentException(
                                    "Producto no encontrado: " + item.getProductId()));

                    if (product.getStock() < item.getQuantity()) {
                        throw new InsufficientStockException(item.getProductId(), item.getQuantity(),
                                product.getStock());
                    }

                    product.setStock(product.getStock() - item.getQuantity());
                    return product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(total);

        log.info("Total calculado: {}", total);

        Order saved = orderRepository.save(order);
        log.info("Orden creada para usuario {} con total ${}", user.getEmail(), total);
        log.info("Total guardado en DB: {}", saved.getTotalPrice());

        return new OrderResponseDTO(
                saved.getId(),
                saved.getItemsOrder(),
                user.getEmail(),
                saved.getTotalPrice());
    }

    @Override
    public List<OrderResponseDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderResponseDTO(
                        order.getId(),
                        order.getItemsOrder(),
                        order.getUser().getEmail(),
                        order.getTotalPrice()))
                .toList();
    }

    @Override
    public List<OrderResponseDTO> findByUserId(Long userId) {
        return orderRepository.findByUser_Id(userId).stream()
                .map(order -> new OrderResponseDTO(
                        order.getId(),
                        order.getItemsOrder(),
                        order.getUser().getEmail(),
                        order.getTotalPrice()))
                .toList();
    }

}
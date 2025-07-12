package com.techlab.app.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Long productId, int requested, int available) {
        super("Stock insuficiente para el producto ID " + productId +
                ": solicitado " + requested + ", disponible " + available);
    }
}
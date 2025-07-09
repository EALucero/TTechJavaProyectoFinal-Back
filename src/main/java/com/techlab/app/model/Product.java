package com.techlab.app.model;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 140)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    private String image;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    // Getters, Setters, Constructors
    
    
}
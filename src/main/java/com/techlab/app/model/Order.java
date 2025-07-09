package com.techlab.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(columnDefinition = "json")
    private String itemsOrder;

    // Getters, Setters, Constructors
}

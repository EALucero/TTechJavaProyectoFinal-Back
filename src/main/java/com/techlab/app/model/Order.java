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

    public Order() {}

    public Order(Long id, User user, String itemsOrder) {
        this.id = id;
        this.user = user;
        this.itemsOrder = itemsOrder;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getItemsOrder() { return itemsOrder; }
    public void setItemsOrder(String itemsOrder) { this.itemsOrder = itemsOrder; }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", itemsOrder='" + itemsOrder + '\'' +
                '}';
    }
}
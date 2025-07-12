package com.techlab.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.techlab.app.dto.ItemOrderDTO;
import com.techlab.app.config.ItemsOrderConverter;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Convert(converter = ItemsOrderConverter.class)
    @Column(columnDefinition = "json")
    private List<ItemOrderDTO> itemsOrder;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<ItemOrderDTO> getItemsOrder() { return itemsOrder; }
    public void setItemsOrder(List<ItemOrderDTO> itemsOrder) { this.itemsOrder = itemsOrder; }

    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", itemsOrder='" + itemsOrder +
                ", totalPrice"+ totalPrice + '\'' +
                '}';
    }

}
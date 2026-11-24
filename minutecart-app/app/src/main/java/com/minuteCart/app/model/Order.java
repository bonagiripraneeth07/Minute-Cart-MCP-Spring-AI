package com.minuteCart.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String customerName;
    private String deliveryAddress;

    private BigDecimal totalAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime estimatedDeliveryTime = LocalDateTime.now().plusMinutes(10);

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem item){
        this.items.add(item);
        item.setOrder(this);
    }
    public void removeItem(OrderItem item){
        this.items.remove(item);
        item.setOrder(null);
    }
}

package com.minutecart.mcp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Order {

    private Long orderId;
    private String customerName;
    private String deliveryAddress;

    private BigDecimal totalAmount;

    private Status status;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime estimatedDeliveryTime = LocalDateTime.now().plusMinutes(10);


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

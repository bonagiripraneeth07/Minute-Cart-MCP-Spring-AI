package com.minutecart.mcp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

 @Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

public class OrderItem {

    private Long id;


    @JsonBackReference
    private Order order;


    private Product product;

    private int quantity;
    private BigDecimal priceAtPurchase;
}
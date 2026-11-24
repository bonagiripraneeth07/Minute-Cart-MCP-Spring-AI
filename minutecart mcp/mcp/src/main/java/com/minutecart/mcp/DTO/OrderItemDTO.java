package com.minutecart.mcp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
         private Long id;
         //private Order order;
        private ProductDTO product;
        private int quantity;
        private BigDecimal priceAtPurchase;
    }


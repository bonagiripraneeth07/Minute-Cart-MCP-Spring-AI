package com.minutecart.mcp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data

@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long productID;
    private String productName;
    private String category;
    private BigDecimal price;
    private int stockQuantity;
    private String units;
    private String description;
    private String brand;
}

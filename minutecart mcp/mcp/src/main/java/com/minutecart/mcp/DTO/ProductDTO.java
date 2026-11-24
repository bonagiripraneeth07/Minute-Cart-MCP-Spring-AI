package com.minutecart.mcp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class ProductDTO {

    private String productName;
    private String category;
    private BigDecimal price;

    private String units;
    private String description;
    private String brand;

    public ProductDTO() {
    }


    public ProductDTO(String productName, String category, BigDecimal price, String units, String description, String brand) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.units = units;
        this.description = description;
        this.brand = brand;
    }

    public ProductDTO(String productName, String category, BigDecimal price, int stockQuantity, String units, String description, String brand) {

    }
}

package com.minutecart.mcp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAddedCart {
    private long productId;
    private int quantity =1;

    @Override
    public String toString() {
        return "ProductAddedCart{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}

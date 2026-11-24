package com.minuteCart.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
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

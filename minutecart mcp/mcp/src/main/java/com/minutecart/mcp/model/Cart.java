package com.minutecart.mcp.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Cart {
    private List<ProductAddedCart> productAddedCarts = new ArrayList<>();
    private BigDecimal totalAmount = BigDecimal.ZERO;

    public void setProductAddedCarts(List<ProductAddedCart> productAddedCarts) {
        this.productAddedCarts = productAddedCarts;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ProductAddedCart> getProductAddedCarts() {
        return productAddedCarts;
    }

    @Override
    public String toString() {
        return "Cart{" +

                ", productAddedCarts=" + productAddedCarts +
                ", totalAmount=" + totalAmount +
                '}';
    }

}


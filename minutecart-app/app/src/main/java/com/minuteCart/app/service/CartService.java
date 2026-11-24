package com.minuteCart.app.service;

import com.minuteCart.app.model.Cart;
import com.minuteCart.app.model.Product;
import com.minuteCart.app.model.ProductAddedCart;
import org.springframework.stereotype.Service;

import java.lang.invoke.CallSite;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final ProductService productService;

    public CartService(ProductService productService) {
        this.productService = productService;
    }
    List<ProductAddedCart> productAddedCartList = new ArrayList<>();
  Cart cart = new Cart();

    public Cart addToCart(ProductAddedCart productAddedCart){
        productAddedCartList.add(productAddedCart);
        System.out.println(productAddedCartList.size());
        cart.setProductAddedCarts(productAddedCartList);
         calculateTotalAmount(cart);
        return cart;
    }

    public BigDecimal calculateTotalAmount(Cart cart) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (ProductAddedCart cartItem : cart.getProductAddedCarts()) {
            Product product = productService.getProduct(cartItem.getProductId());

            // Multiply price by quantity cleanly instead of looping
            BigDecimal lineTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));

            totalAmount = totalAmount.add(lineTotal);
        }

        cart.setTotalAmount(totalAmount);
        return totalAmount;
    }

}

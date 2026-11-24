package com.minuteCart.app.controller;

import com.minuteCart.app.DTO.ProductDTO;
import com.minuteCart.app.model.*;
import com.minuteCart.app.service.CartService;
import com.minuteCart.app.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class ProductController {
    final private ProductService service;
    final private CartService cartService;
    public ProductController(ProductService service, CartService cartService){
        this.service = service;
        this.cartService = cartService;
    }

    @GetMapping("getUser")
    public User getUserInfo(){
         Cart cart = new Cart();
       Product product = service.getProduct(1L);
       // Product product1 = service.getProduct(2L);
         ProductAddedCart productad1 = new ProductAddedCart();
         ProductAddedCart productad2 = new ProductAddedCart();
         productad1.setProductId(1L);
         productad1.setQuantity(2);
         productad2.setProductId(2L);

       System.out.println(product.getBrand());
      //  System.out.println(product.getBrand());
         List<ProductAddedCart>products = new ArrayList<>();
        products.add(productad1);
        products.add(productad2);
         cart.setProductAddedCarts(products);
        System.out.println();
         cartService.calculateTotalAmount(cart);
        System.out.println(cart);


      return service.getUser();
    }

    @GetMapping("getProducts")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    @GetMapping("getOrders")
    public List<Order> getOrders(){
        return service.getOrders();
    }

    @GetMapping("/getProductsByName/{productName}")
    public List<ProductDTO> getProductsByProductName(@PathVariable String productName){
        return service.getProductsByProductName(productName);
    }

    @GetMapping("getProductByName/{productName}")
    public ProductDTO getProductByName(@PathVariable String productName){
        return service.getProductByName(productName);
    }

    @GetMapping("getProductsByCategory/{category}")
    public List<ProductDTO> getProductsByCategory(@PathVariable String category){
        return service.getProductsByCategory(category);
    }

    @GetMapping("getProductsByBrand/{brandName}")
    public List<ProductDTO> getProductsByBrand(@PathVariable String brandName){
        return service.getProductsByBrand(brandName);
    }
    @GetMapping("getProductId/{productName}")
    public Long getProductId(@PathVariable String productName){
        return service.getProductId(productName);
    }

}

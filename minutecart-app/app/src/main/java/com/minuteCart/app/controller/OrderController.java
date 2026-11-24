package com.minuteCart.app.controller;

import com.minuteCart.app.DTO.OrderDTO;
import com.minuteCart.app.DTO.OrderItemDTO;
import com.minuteCart.app.DTO.ProductDTO;
import com.minuteCart.app.model.*;
import com.minuteCart.app.repo.ProductRepository;
import com.minuteCart.app.service.CartService;
import com.minuteCart.app.service.ProductService;
import com.minuteCart.app.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final CartService cartService;
    private OrderService orderService;
    private ProductRepository repository;
    private ProductService service;
    private Cart cart = new Cart();
    List<ProductAddedCart> productAddedCarts = new ArrayList<>();
    public OrderController(ProductService service, OrderService orderService, ProductRepository productRepository, CartService cartService) {
        this.orderService = orderService;
        this.repository= productRepository;
        this.service = service;
        this.cartService = cartService;
    }

    @PostMapping("addtocart")
    public Cart addToCart(@RequestBody ProductAddedCart productAddedCart){
        productAddedCarts.add(productAddedCart);
        System.out.println(productAddedCarts.size());
         cart.setProductAddedCarts(productAddedCarts);
         cartService.calculateTotalAmount(cart);
        System.out.println(  cartService.calculateTotalAmount(cart));
         return cart;

    }

    @GetMapping("viewcart")
    public Cart viewCart( ){
      return cart;
    }


    @GetMapping("create")
    public ResponseEntity<?> createOrder(){
        if(cart==null|| cart.getProductAddedCarts().isEmpty()){
            return  ResponseEntity.badRequest().body("Cart is Empty");
        }
           Order savedOrder= orderService.createOrder(cart);
           clearCart();
           return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("orders")
    public List<OrderDTO> getOrders(){

        return orderService.getOrders();
    }

    @GetMapping("clearcart")
    public String clearCart(){
        productAddedCarts.clear();
        cart= new Cart();
        return "Your Cart is Empty...";
    }

    @GetMapping("getRecentOrders")
    public List<OrderDTO> getRecentOrders(){
      return   orderService.getFrequentOrders();
    }

    @GetMapping("getStatus")
    public ResponseEntity<?> getStatus(){
        List<OrderDTO> pendingOrders= orderService.getDeliveryStatus();
        if(pendingOrders.isEmpty()){
            return   ResponseEntity.ok("No pending orders. All past orders delivered.");
        }
        return   ResponseEntity.ok(pendingOrders);
    }

}

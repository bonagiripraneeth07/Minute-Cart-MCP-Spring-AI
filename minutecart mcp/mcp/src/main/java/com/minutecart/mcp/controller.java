package com.minutecart.mcp;

import com.minutecart.mcp.DTO.ProductDTO;
import com.minutecart.mcp.model.Order;
import com.minutecart.mcp.model.Product;
import com.minutecart.mcp.model.User;
import com.minutecart.mcp.service.ProductMcpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class controller {

    @Autowired
    private ProductMcpServices services;
    @GetMapping("/user")
    public User getuser(){
        return services.getUser();
    }

    @GetMapping("getProducts")
    public List<Product> getProducts(){
        return services.getProducts();
    }

//    @GetMapping("getOrders")
//    public List<Order> getOrders(){
//        return services.getOrders();
//    }



}

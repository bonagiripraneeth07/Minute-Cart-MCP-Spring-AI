package com.minutecart.mcp.service;

import com.minutecart.mcp.DTO.ProductDTO;
import com.minutecart.mcp.model.Order;
import com.minutecart.mcp.model.Product;
import com.minutecart.mcp.model.User;
import jakarta.servlet.ServletConfig;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;


@Service

public class ProductMcpServices {

    private    RestClient userServiceClient;

    public ProductMcpServices(RestClient userServiceClient ) {
        this.userServiceClient = userServiceClient;

    }

    @Tool(name = "get_user_details",description =  " when user asks to fetch the user details ,this method is called to fetch user details.")
    public   User getUser(){
         return   userServiceClient.get()
                .uri("getUser")
                .retrieve()
                .body(User.class);

    }
    @Tool(name="get_products",description = "when user ask to  list out available products or products from the minute cart this method is executed. ")
    public List<Product> getProducts(){
        return  userServiceClient.get()
                .uri("getProducts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Product>>() {});
    }

//    @Tool(name = "getOrders",description = "when user aks to fetch all the orders that they have ordered. this method is executed and a list of orders are returned.")
//    public List<Order> getOrders(){
//        return  userServiceClient.get()
//                .uri("getOrders")
//                .retrieve()
//                .body(new ParameterizedTypeReference<List<Order>>() {});
//    }
    @Tool(name = "get_products_by_productName",description = "Searches the database for all products that partially match or are similar to a keyword or category. Use this when the user gives a broad term, partial name, or wants to browse options (e.g., 'butter', 'dairy', 'milk').")
    public List<ProductDTO> getProductsByProductName(  String productName){
        return userServiceClient.get()
                .uri("/getProductsByName/{productName}",productName)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProductDTO>>() {});
    }

    @Tool(name = "get_productId",description = "Takes a product name as argument and returns the productID.")
    public Long getProductId(  String productName){
        return  userServiceClient.get()
                .uri("getProductId/{productName}",productName)
                .retrieve()
                .body(Long.class);
    }
    @Tool(name="get_product_by_productName",description = "Fetches a single product using its exact, complete product name. Use this only when the user asks for a specific known item (e.g., 'Amul Pasteurised Butter').")
    public ProductDTO getProductByName( String productName){
        return userServiceClient.get()
                .uri("getProductByName/{productName}",productName)
                .retrieve()
                .body(ProductDTO.class);
    }

    @Tool(name="get_products_by_category",description = "Fetches a list of products that falls into same category,example:(milk under dairy")
    public List<ProductDTO> getProductsByCategory(  String category){
        return userServiceClient.get()
                .uri("getProductsByCategory/{category}",category)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProductDTO>>() {});
    }

    @Tool(name="get_products_by_brand",description = "Fetches a list of Products with the help of  brand name,example(amul,dairy milk)")
    public List<ProductDTO> getProductsByBrand( String brandName){
        return userServiceClient.get()
                .uri("getProductsByBrand/{brandName}",brandName)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProductDTO>>() {});
    }

}

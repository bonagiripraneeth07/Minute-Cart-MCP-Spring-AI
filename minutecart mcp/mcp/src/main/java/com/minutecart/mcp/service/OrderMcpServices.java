package com.minutecart.mcp.service;

import com.minutecart.mcp.DTO.OrderDTO;
import com.minutecart.mcp.model.Cart;
import com.minutecart.mcp.model.ProductAddedCart;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OrderMcpServices {
    private RestClient userServiceClient;

    public OrderMcpServices(RestClient userServiceClient) {
        this.userServiceClient = userServiceClient;

    }

    @Tool(name = "add_To_Cart",description = "adds products to cart ,takes product id and quantity and adds into cart. when user says 'add amul Cheese Slices Plain' check for the id of that product using other tools and add to cart with quantity if mentioned.")
    public Cart addToCart(  ProductAddedCart productAddedCart){
        return userServiceClient.post()
                .uri("order/addtocart")
                .body(productAddedCart)
                .retrieve()
                .body(Cart.class);
    }
    @Tool(name = "view_cart",description = "Fetches the cart details from minute cart.")
    public Cart viewCart( ){
        return userServiceClient.get()
                .uri("order/viewcart")
                .retrieve()
                .body(Cart.class);
    }
    @Tool(name = "create_order",description = "Creates order by looking into the products added into cart. if cart is empty it returns that cart is empty.")
    public String createOrder(){
        try {
            return userServiceClient.get()
                    .uri("order/create")
                    .retrieve()
                    .body(String.class);
        } catch (HttpClientErrorException.BadRequest ex) {
            return ex.getResponseBodyAsString();
        }
    }

    @Tool(name = "get_orders",description = "when user aks to fetch all the orders that they have ordered. this method is executed and a list of orders are returned.")
    public List<OrderDTO> getOrders() {
        return userServiceClient.get()
                .uri("order/orders")
                .retrieve()
                .body(new ParameterizedTypeReference<List<OrderDTO>>() {});
    }
    @Tool(name = "clear_cart",description = "when user want to remove products from the cart.")
    public String clearCart(){
        return userServiceClient.get()
                .uri("order/clearcart")
                .retrieve()
                .body(String.class);
    }

    @Tool(name = "get_recent_orders",description = "Fetch last 4 order details.")
    public List<OrderDTO> getRecentOrders(){
        return userServiceClient.get()
                .uri("order/getRecentOrders")
                .retrieve()
                .body(new ParameterizedTypeReference<List<OrderDTO>>() {});
    }

    @Tool(name = "get_Order_Status",description = "Fetch status of Pending Orders.")
    public String getStatus(){
        return userServiceClient.get()
                .uri("order/getStatus")
                .retrieve()
                .body(String.class);
    }

}

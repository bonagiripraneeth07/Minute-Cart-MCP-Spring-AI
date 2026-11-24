package com.minuteCart.app.service;

import com.minuteCart.app.DTO.OrderDTO;
import com.minuteCart.app.DTO.OrderItemDTO;
import com.minuteCart.app.DTO.ProductDTO;
import com.minuteCart.app.model.*;
import com.minuteCart.app.repo.OrderItemRepository;
import com.minuteCart.app.repo.OrderRepository;
import com.minuteCart.app.repo.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Service
public class OrderService {
    final private OrderRepository orderRepository;
    final private ProductRepository productRepository;
    final private User user;
    final private ProductService productService;
    final private CartService cartService;
    final private ThreadPoolTaskScheduler  taskScheduler;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, ProductService productService, CartService cartService,  ThreadPoolTaskScheduler scheduler) {
        this.orderRepository= orderRepository;
        this.productRepository = productRepository;
        this.productService = productService;
       this.user= productService.getUser();
        this.cartService =cartService;
        this.taskScheduler = scheduler;
    }


    public Order createOrder(Cart cart) {
        Order order = new Order();
        for( ProductAddedCart cartProduct : cart.getProductAddedCarts()){
            OrderItem item = new OrderItem();
            item.setProduct(productService.getProduct(cartProduct.getProductId()));
            item.setQuantity(cartProduct.getQuantity());
            item.setOrder(order);
            item.setPriceAtPurchase(productService.getProduct(cartProduct.getProductId()).getPrice());
            order.addItem(item);
        }
        order.setCustomerName(user.getUserName());
        cartService.calculateTotalAmount(cart);
        order.setTotalAmount(cart.getTotalAmount());

        order.setDeliveryAddress(user.getAddress());
         order.setStatus(Status.PROCESSING_ORDER);
          Order savedOrder= orderRepository.save(order);
        deliveryStatusUpdate(order.getOrderId());
        return savedOrder;
    }

    public List<OrderDTO> getOrders() {
           List<Order> foundOrders = orderRepository.findAll();
           return convertOrderToOrderDTO(foundOrders);

    }

    public List<OrderDTO> getFrequentOrders(){
         List<Order> foundOrders = orderRepository.findTop4ByOrderByCreatedAtDesc();
         return convertOrderToOrderDTO(foundOrders);
    }

    public List<OrderDTO>  getDeliveryStatus(){
        List <Order> foundOrders = orderRepository.findByStatusNot(Status.DELIVERED);
        return convertOrderToOrderDTO(foundOrders);

    }

    public List<OrderDTO> convertOrderToOrderDTO(List<Order> foundOrders){
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : foundOrders) {

            List<OrderItemDTO> orderItemsDTO = new ArrayList<>();

            for (OrderItem item : order.getItems()) {
                OrderItemDTO dto = new OrderItemDTO();
                dto.setId(item.getId());
                dto.setPriceAtPurchase(item.getPriceAtPurchase());
                dto.setQuantity(item.getQuantity());


                Product p = item.getProduct();
                ProductDTO productDTO = new ProductDTO(
                        p.getProductName(),
                        p.getCategory(),
                        p.getPrice(),
                        p.getUnits(),
                        p.getDescription(),
                        p.getBrand()
                );
                dto.setProduct(productDTO);


                orderItemsDTO.add(dto);
            }

            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(order.getOrderId());
            orderDTO.setCustomerName(order.getCustomerName());
            orderDTO.setDeliveryAddress(order.getDeliveryAddress());
            orderDTO.setTotalAmount(order.getTotalAmount());
            orderDTO.setStatus(order.getStatus());
            orderDTO.setCreatedAt(order.getCreatedAt());
            orderDTO.setEstimatedDeliveryTime(order.getEstimatedDeliveryTime());
            orderDTO.setItems(orderItemsDTO);

            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    public void updateDeliveryStatus(Status status,Long id){
        orderRepository.updateStatus(id,status);
        printSchedulerStats();
    }

    public void deliveryStatusUpdate( Long id){
        Instant start = Instant.now();

        System.out.println("processing your order.");

        taskScheduler.schedule(()->{
            updateDeliveryStatus(Status.PACKING,id);
            System.out.println("Your order is being packed....");
        },start.plus(2, ChronoUnit.MINUTES));

        taskScheduler.schedule(()->{
            updateDeliveryStatus(Status.OUT_FOR_DELIVERY,id);
            System.out.println("your order is out for delivery...");
        },start.plus(5,ChronoUnit.MINUTES));
        taskScheduler.schedule(()->{
            updateDeliveryStatus(Status.DELIVERED,id);
            System.out.println("your order delivered...");
        },start.plus(10,ChronoUnit.MINUTES));
    }
    public void printSchedulerStats( ) {
        // 1. Threads actively executing a task right now
        int activeThreads = taskScheduler.getActiveCount();

        // 2. Access underlying executor for queued/scheduled tasks
        ScheduledThreadPoolExecutor executor = taskScheduler.getScheduledThreadPoolExecutor();

        // Tasks waiting in the queue (e.g., waiting for the 2-min, 5-min, or 10-min mark)
        int queuedTasks = executor.getQueue().size();

        // Total tasks submitted since startup
        long totalSubmitted = executor.getTaskCount();

        // Total tasks finished so far
        long completedTasks = executor.getCompletedTaskCount();

        System.out.printf(
                "Active right now: %d | Waiting in queue: %d | Completed: %d | Total submitted: %d%n",
                activeThreads, queuedTasks, completedTasks, totalSubmitted
        );
    }
}


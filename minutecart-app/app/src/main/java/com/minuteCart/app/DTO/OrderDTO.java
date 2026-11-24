package com.minuteCart.app.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.minuteCart.app.model.OrderItem;
import com.minuteCart.app.model.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class OrderDTO {

    private Long orderId;
    private String customerName;
    private String deliveryAddress;

    private BigDecimal totalAmount;

    private Status status;

    private LocalDateTime createdAt ;
    private LocalDateTime estimatedDeliveryTime ;

    private List<OrderItemDTO> items = new ArrayList<>();
}

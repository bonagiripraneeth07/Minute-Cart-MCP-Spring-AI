package com.minutecart.mcp.DTO;

import com.minutecart.mcp.model.Status;

import lombok.Getter;
import lombok.Setter;

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

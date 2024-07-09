package com.example.shoppingserver.domain.order.dto;


import com.example.shoppingserver.domain.order.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long orderId;
    private String userName;
    private String orderStatus;
    private List<OrderItem> orderItems;
    private int sumPrice;
}

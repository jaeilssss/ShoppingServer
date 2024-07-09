package com.example.shoppingserver.domain.order.dto;

import com.example.shoppingserver.domain.item.entity.Item;
import com.example.shoppingserver.domain.order.entity.Order;
import com.example.shoppingserver.domain.order.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
    private Long itemId;
    private int amount;


    public OrderItem toEntity(Item item, Order order) {
        return OrderItem.builder()
                .amount(amount)
                .item(item)
                .order(order)
                .build();
    }
}

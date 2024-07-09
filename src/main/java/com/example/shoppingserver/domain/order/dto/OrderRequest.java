package com.example.shoppingserver.domain.order.dto;

import com.example.shoppingserver.domain.item.entity.Item;
import com.example.shoppingserver.domain.item.enums.ItemErrorCode;
import com.example.shoppingserver.domain.member.entity.Member;
import com.example.shoppingserver.domain.order.entity.Order;
import com.example.shoppingserver.domain.order.entity.OrderItem;
import com.example.shoppingserver.domain.order.enums.OrderErrorCode;
import com.example.shoppingserver.domain.order.enums.OrderStatus;
import com.example.shoppingserver.globals.exception.MyException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private List<OrderItemRequest> orderItemRequestList;


    public Order toEntity(Member member, List<Item> itemList) {
        Order order = new Order();
        int sumPrice = 0;
        for(int i = 0; i<orderItemRequestList.size(); i++) {
            OrderItemRequest orderItemRequest = orderItemRequestList.get(i);
            if (itemList.get(i).getStockQuantity() - orderItemRequest.getAmount() < 0) {
                throw new MyException(
                        ItemErrorCode.STOCK_QUANTITY_ERROR.getCode(),
                        ItemErrorCode.STOCK_QUANTITY_ERROR.getMessage());
            }
            sumPrice += itemList.get(i).getPrice();
            itemList.get(i).removeStockQuantity(orderItemRequest.getAmount());
            order.getOrderItems().add(orderItemRequest.toEntity(itemList.get(i),order));
        }
        order.setMember(member);
        order.setOrderStatus(OrderStatus.ORDER);
        order.setSumPrice(sumPrice);
        return order;

    }
}

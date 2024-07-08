package com.example.shoppingserver.domain.order.service;

import com.example.shoppingserver.domain.item.dao.ItemRepository;
import com.example.shoppingserver.domain.item.entity.Item;
import com.example.shoppingserver.domain.item.enums.ItemErrorCode;
import com.example.shoppingserver.domain.member.dao.MemberRepository;
import com.example.shoppingserver.domain.member.entity.Member;
import com.example.shoppingserver.domain.member.enums.MemberErrorCodes;
import com.example.shoppingserver.domain.order.dao.OrderRepository;
import com.example.shoppingserver.domain.order.dto.OrderItemRequest;
import com.example.shoppingserver.domain.order.dto.OrderRequest;
import com.example.shoppingserver.domain.order.dto.OrderResponse;
import com.example.shoppingserver.domain.order.entity.Order;
import com.example.shoppingserver.domain.order.enums.OrderErrorCode;
import com.example.shoppingserver.globals.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    public OrderResponse getOrderDetail(Long orderId) {
        return getOrderData(orderId).createOrderResponse();
    }

    public Order getOrderData(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> {throw new MyException(
                        OrderErrorCode.INVALID_ORDER_ID.getCode(),
                        OrderErrorCode.INVALID_ORDER_ID.getMessage());
                });
    }

    @Transactional
    public void order(Long memberId, OrderRequest request) {
        Member consumer = getMember(memberId);
        List<Item> itemList = getItemList(request);
        Order order = request.toEntity(consumer, itemList);
        orderRepository.save(order);
    }

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MyException(
                        MemberErrorCodes.INCORRECT_MEMBER_INFO.getCode(),
                        MemberErrorCodes.INCORRECT_MEMBER_INFO.getMessage()));
    }

    @Transactional
    public List<Item> getItemList(OrderRequest itemList) {
        ArrayList<Item> itemArrayList = new ArrayList<>();
        itemList.getOrderItemRequestList().forEach(
                it -> itemArrayList.add(itemRepository.findById(it.getItemId())
                        .orElseThrow(() -> new MyException(
                                ItemErrorCode.INVALID_ITEM_ID.getCode(),
                                ItemErrorCode.INVALID_REQUEST.getMessage())))
        );
        return itemArrayList;
    }


}

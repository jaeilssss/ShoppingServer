package com.example.shoppingserver.domain.order.entity;

import com.example.shoppingserver.domain.member.entity.Member;
import com.example.shoppingserver.domain.order.dto.OrderResponse;
import com.example.shoppingserver.domain.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Getter @Setter
@ToString
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private int sumPrice;

    public OrderResponse createOrderResponse() {
        return OrderResponse.builder()
                .orderId(orderId)
                .userName(member.getName())
                .orderStatus(orderStatus.name())
                .orderItems(orderItems)
                .sumPrice(sumPrice)
                .build();
    }
}

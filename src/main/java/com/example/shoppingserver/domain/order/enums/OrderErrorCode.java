package com.example.shoppingserver.domain.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderErrorCode {

    INVALID_ORDER_ID("Order_001", "유효하지않은 orderId입니다.(주문 데이터 검색x)");

    private String code;
    private String message;
}

package com.example.shoppingserver.domain.item.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ItemErrorCode {

    INVALID_REQUEST("Item_001","잘못된 요청 입니다"),
    INVALID_ITEM_ID("Item_002", "잘못된 Item Id 입니다"),
    STOCK_QUANTITY_ERROR("Item_003","구매 수량이 재고량 보다 많습니다");

    private final String code;
    private final String message;

}

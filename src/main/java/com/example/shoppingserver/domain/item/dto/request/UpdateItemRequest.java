package com.example.shoppingserver.domain.item.dto.request;

import lombok.Getter;

@Getter
public class UpdateItemRequest {

    private Long itemId;
    private Long categoryId;

    private String name;
    private int amount;
    private Long memberId;

}

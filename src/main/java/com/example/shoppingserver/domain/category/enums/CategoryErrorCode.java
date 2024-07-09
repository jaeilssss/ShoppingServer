package com.example.shoppingserver.domain.category.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryErrorCode {
    NOT_EXIST_CATEGORY("CATEGORY_001","존재 하지 않은 카테고리 ID 입니다.");

    private final String code;
    private final String message;

}

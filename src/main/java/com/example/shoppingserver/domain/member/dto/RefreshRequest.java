package com.example.shoppingserver.domain.member.dto;

import lombok.Getter;

@Getter
public class RefreshRequest {
    private Long memberId;
    private String refreshToken;
}

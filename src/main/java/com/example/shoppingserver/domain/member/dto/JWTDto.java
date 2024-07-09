package com.example.shoppingserver.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JWTDto {
    String accessToken;
    String refreshToken;
}

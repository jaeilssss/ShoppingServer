package com.example.shoppingserver.domain.member.dto;

import io.jsonwebtoken.Jwt;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenResponse {
    public String accessToken;
    public String refreshToken;

    public TokenResponse(JWTDto jwtDto) {
        this.accessToken = jwtDto.getAccessToken();
        this.refreshToken = jwtDto.getRefreshToken();
    }
}

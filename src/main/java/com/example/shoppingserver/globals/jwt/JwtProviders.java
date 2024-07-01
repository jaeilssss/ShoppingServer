package com.example.shoppingserver.globals.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtProviders implements InitializingBean {
    @Value("${jwt.headers}")
    public static String AUTHORITIES_KEY;
    private final String secretKey;
    private final long expirationTime;
    private Key key;

    public JwtProviders(
            @Value("${jwt.secretKey}") String secretKey,
            @Value("${jwt.expiration}") long expirationTime) {
        this.secretKey = secretKey;
        this.expirationTime = expirationTime;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey.replace(" ",""));
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String email, Long userId) {
        Claims claims = Jwts.claims();

        claims.put(AUTHORITIES_KEY, email);

        Long now = new Date().getTime();
        Date validity = new Date(now + this.expirationTime);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(validity)
                .claim("userId",userId)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException |
                MalformedJwtException e) {
            log.error("Invalid Jwt Token");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty",  e);
        }
        return false;
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if(claims.get("userId") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰 입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("userId").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(claims.get("userId"), "", authorities);
    }

    private Claims parseClaims(String token) {
        try {

        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}

package com.example.shoppingserver.globals.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtProviders jwtProviders;

    public JwtAuthenticationFilter(JwtProviders jwtProviders) {
        this.jwtProviders = jwtProviders;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        String token = resolveToken((HttpServletRequest) servletRequest);
        System.out.println("JwtAuthenticationFilter !!! ");
        if(token != null && jwtProviders.validateToken(token)) {
            Authentication authentication = jwtProviders.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearToken) &&
            bearToken.startsWith("Bearer")) {
            return bearToken.substring(7);
        }
        return null;
    }
}

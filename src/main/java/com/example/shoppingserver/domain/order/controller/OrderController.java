package com.example.shoppingserver.domain.order.controller;

import com.example.shoppingserver.domain.order.dto.OrderItemRequest;
import com.example.shoppingserver.domain.order.dto.OrderRequest;
import com.example.shoppingserver.domain.order.dto.OrderResponse;
import com.example.shoppingserver.domain.order.service.OrderService;
import com.example.shoppingserver.globals.Response;
import com.example.shoppingserver.globals.http.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public Response<OrderResponse> getOrderDetail(@PathVariable("orderId") Long orderId) {
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                orderService.getOrderDetail(orderId)
        );
    }

    @PostMapping("/order/{memberId}")
    public Response<Void> order(@PathVariable("memberId") Long memberId, @RequestBody OrderRequest requests) {
        orderService.order(memberId, requests);
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                null
        );
    }


}

package com.example.shoppingserver.domain.item.controller;

import com.example.shoppingserver.domain.item.dto.request.UpdateItemRequest;
import com.example.shoppingserver.domain.item.dto.response.ItemResponse;
import com.example.shoppingserver.domain.item.dto.request.ItemRequest;
import com.example.shoppingserver.domain.item.service.ItemService;
import com.example.shoppingserver.globals.Response;
import com.example.shoppingserver.globals.http.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/item")
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public Response<List<ItemResponse>> getAllItemList() {
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                itemService.getAllItemList()
        );
    }

    @PostMapping("/add")
    public Response<Void> addItem(@RequestBody ItemRequest request) {
        itemService.addItem(request);
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                null
        );
    }

    @GetMapping("/get/{itemId}")
    public Response<ItemResponse> getItem(@PathVariable("itemId")Long itemId) {
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                itemService.getItem(itemId)
        );
    }

    @PutMapping("/update/{itemId}")
    public Response<Void> updateItem(@PathVariable("itemId") long itemId, @RequestBody ItemRequest request) {
        itemService.updateItem(itemId, request);
        return new Response<>(
                ApiResponse.OK,
                ApiResponse.OK_MESSAGE,
                null
        );
    }
}

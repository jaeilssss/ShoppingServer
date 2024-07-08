package com.example.shoppingserver.domain.item.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ItemResponse {
    private Long itemId;
    private String name;
    private Long categoryId;
    private int price;
    private int stockQuantity;
    private String companyName;

    private AlbumResponse album;
    private BookResponse book;
    private ClothesResponse clothes;
    private FoodResponse food;

}

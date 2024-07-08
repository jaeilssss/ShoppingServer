package com.example.shoppingserver.domain.item.dto.request;

import com.example.shoppingserver.domain.item.enums.ItemErrorCode;
import com.example.shoppingserver.domain.item.entity.*;
import com.example.shoppingserver.globals.exception.MyException;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ItemRequest {
    private String name;
    private Long categoryId;

    private int price;
    private int stockQuantity;
    private String companyName;
    private String itemType;

    private BookRequest book;
    private FoodRequest food;
    private ClothesRequest clothes;
    private AlbumRequest album;

    public Item toEntity() {
        if(book != null) {
            Book book = new Book();
            book.setItemData(this);
            return book;
        } else if(food != null) {
            Food food = new Food();
            food.setItemData(this);
            return food;
        } else if(clothes != null) {
            Clothes clothes = new Clothes();
            clothes.setItemData(this);
        } else if(album != null) {
            Album album = new Album();
            album.setItemData(this);
        }
        throw new MyException(ItemErrorCode.INVALID_REQUEST.getCode(), ItemErrorCode.INVALID_REQUEST.getMessage());
    }
}

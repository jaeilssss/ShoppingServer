package com.example.shoppingserver.domain.item.entity;

import com.example.shoppingserver.domain.item.dto.request.ItemRequest;
import com.example.shoppingserver.domain.item.dto.response.BookResponse;
import com.example.shoppingserver.domain.item.dto.response.ItemResponse;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
@ToString
public class Book extends Item {
    private String author;
    @Override
    protected void setData(ItemRequest request) {
        author = request.getBook().getAuthor();
    }


    @Override
   public ItemResponse createItemResponse() {
        return setItemResponseBuilder()
                .book(new BookResponse(author))
                .build();
   }
}

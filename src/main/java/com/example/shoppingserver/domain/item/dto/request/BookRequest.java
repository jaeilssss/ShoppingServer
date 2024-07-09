package com.example.shoppingserver.domain.item.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookRequest extends ItemRequest {
    private String author;
}

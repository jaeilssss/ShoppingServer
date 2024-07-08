package com.example.shoppingserver.domain.item.dto.response;

import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FoodResponse {
    private String expirationDate;
    private ArrayList<String> nutrientList;
}

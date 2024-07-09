package com.example.shoppingserver.domain.item.entity;

import com.example.shoppingserver.domain.item.dto.request.ItemRequest;
import com.example.shoppingserver.domain.item.dto.response.FoodResponse;
import com.example.shoppingserver.domain.item.dto.response.ItemResponse;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@DiscriminatorValue("D")
public class Food extends Item{

    private String expirationDate;
    private ArrayList<String> nutrientList;

    @Override
    protected void setData(ItemRequest request) {
        expirationDate = request.getFood().getExpirationDate();
        nutrientList = request.getFood().getNutrientList();
        System.out.println(expirationDate);
    }

    @Override
    public ItemResponse createItemResponse() {
        return setItemResponseBuilder()
                .food(new FoodResponse(expirationDate, nutrientList))
                .build();
    }

}

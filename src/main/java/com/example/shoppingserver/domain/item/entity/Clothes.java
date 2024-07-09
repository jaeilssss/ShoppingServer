package com.example.shoppingserver.domain.item.entity;

import com.example.shoppingserver.domain.item.dto.request.ItemRequest;
import com.example.shoppingserver.domain.item.dto.response.ClothesResponse;
import com.example.shoppingserver.domain.item.dto.response.ItemResponse;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("C")
public class Clothes extends Item {
    private String size;

    @Override
    protected void setData(ItemRequest request) {
        size = request.getClothes().getSize();
    }

    @Override
    public ItemResponse createItemResponse() {
        return setItemResponseBuilder()
                .clothes(new ClothesResponse(size))
                .build();
    }
}

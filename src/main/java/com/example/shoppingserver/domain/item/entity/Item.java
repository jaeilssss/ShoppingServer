package com.example.shoppingserver.domain.item.entity;

import com.example.shoppingserver.domain.item.dto.request.ItemRequest;
import com.example.shoppingserver.domain.item.dto.request.UpdateItemRequest;
import com.example.shoppingserver.domain.item.dto.response.ItemResponse;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String name;

    private Long categoryId;
    private int price;
    private int stockQuantity;
    private String companyName;
    public void setItemData(ItemRequest request){
        name = request.getName();
        categoryId = request.getCategoryId();
        price = request.getPrice();
        stockQuantity = request.getStockQuantity();
        companyName = request.getCompanyName();
        setData(request);
    }

    protected abstract void setData(ItemRequest request);
    public abstract ItemResponse createItemResponse();
    protected ItemResponse.ItemResponseBuilder setItemResponseBuilder() {
        return ItemResponse.builder()
                .itemId(itemId)
                .name(name)
                .categoryId(categoryId)
                .price(price)
                .stockQuantity(stockQuantity)
                .companyName(companyName);
    }

    public void removeStockQuantity(int amount) {
        stockQuantity -= amount;
    }
}

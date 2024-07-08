package com.example.shoppingserver.domain.item.component;

import org.springframework.stereotype.Component;

@Component
public interface ChangeStockQuantity {
    public int changeStockQuantity(String variation, int amount, int stockQuantity);
}

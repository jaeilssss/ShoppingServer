package com.example.shoppingserver.domain.item.component;

import com.example.shoppingserver.domain.item.enums.ItemErrorCode;
import com.example.shoppingserver.globals.exception.MyException;
import org.springframework.stereotype.Component;

@Component
public class ChangeStockQuantityImpl implements ChangeStockQuantity{

    @Override
    public int changeStockQuantity(String variation, int amount, int stockQuantity) {
        if(variation.equals("plus")) {
            return stockQuantity + amount;
        } else if(variation.equals("minus")) {
            if(stockQuantity-amount >0) return stockQuantity - amount;
            else throw new MyException(
                    ItemErrorCode.STOCK_QUANTITY_ERROR.getCode(),
                    ItemErrorCode.STOCK_QUANTITY_ERROR.getMessage());
        } else {
            throw new MyException(
                    ItemErrorCode.INVALID_REQUEST.getCode(),
                    ItemErrorCode.INVALID_REQUEST.getMessage()
            );
        }
    }
}

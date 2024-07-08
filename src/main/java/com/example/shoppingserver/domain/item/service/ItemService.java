package com.example.shoppingserver.domain.item.service;

import com.example.shoppingserver.domain.item.component.ChangeStockQuantity;
import com.example.shoppingserver.domain.item.enums.ItemErrorCode;
import com.example.shoppingserver.domain.item.dao.ItemRepository;
import com.example.shoppingserver.domain.item.dto.response.ItemResponse;
import com.example.shoppingserver.domain.item.dto.request.ItemRequest;
import com.example.shoppingserver.domain.item.entity.Item;
import com.example.shoppingserver.globals.exception.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final ChangeStockQuantity changeStockQuantity;

    public List<ItemResponse> getAllItemList() {
        return itemRepository.findAll()
                .stream().map(Item::createItemResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addItem(ItemRequest request) {
        itemRepository.save(request.toEntity());
    }

    public ItemResponse getItem(Long itemId) {
        return getItemEntity(itemId).createItemResponse();
    }

    @Transactional
    public void updateItem(Long itemId, ItemRequest request) {
        Item item = getItemEntity(itemId);
        item.setItemData(request);
    }

    private Item getItemEntity(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(
                () -> new MyException(
                        ItemErrorCode.INVALID_ITEM_ID.getCode(),
                        ItemErrorCode.INVALID_ITEM_ID.getMessage()));
    }
}

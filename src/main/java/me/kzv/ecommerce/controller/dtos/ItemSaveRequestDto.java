package me.kzv.ecommerce.controller.dtos;

import me.kzv.ecommerce.domain.entity.Category;
import me.kzv.ecommerce.domain.entity.Item;
import me.kzv.ecommerce.domain.entity.ItemImg;
import me.kzv.ecommerce.domain.entity.ItemSize;
import me.kzv.ecommerce.domain.enums.ItemSellStatus;

import java.util.List;

public record ItemSaveRequestDto(
        Long categoryId,
        String itemNm,
        int price,
        ItemSellStatus itemSellStatus,
        List<ItemImg> itemImgs,
        List<ItemSize> itemSizes
) {
    public Item toEntity(Category category) {
        return Item.of(itemNm, price, itemSellStatus, category);
    }
}

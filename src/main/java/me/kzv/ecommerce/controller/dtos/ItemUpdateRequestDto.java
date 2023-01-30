package me.kzv.ecommerce.controller.dtos;

import me.kzv.ecommerce.domain.itemsize.ItemSize;
import me.kzv.ecommerce.domain.item.ItemSellStatus;

import java.util.List;

public record ItemUpdateRequestDto(
        Long itemId,
        Long categoryId,
        String itemNm,
        int price,
        ItemSellStatus itemSellStatus,
        List<ItemUpdateRequestDto> itemImgs,
        List<ItemSize> itemSizes
) {
}

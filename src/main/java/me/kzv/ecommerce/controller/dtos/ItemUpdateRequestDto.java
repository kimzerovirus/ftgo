package me.kzv.ecommerce.controller.dtos;

import me.kzv.ecommerce.domain.entity.ItemImg;
import me.kzv.ecommerce.domain.entity.ItemSize;
import me.kzv.ecommerce.domain.enums.ItemSellStatus;

import java.util.List;

public record ItemUpdateRequestDto(
        Long itemId,
        Long categoryId,
        String itemNm,
        int price,
        ItemSellStatus itemSellStatus,
        List<ItemImg> itemImgs,
        List<ItemSize> itemSizes
) {
}

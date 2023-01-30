package me.kzv.ecommerce.controller.dtos;

import me.kzv.ecommerce.domain.itemimg.ItemImg;
import me.kzv.ecommerce.domain.itemsize.ItemSize;
import me.kzv.ecommerce.domain.item.ItemSellStatus;

import java.util.List;

public record ItemSaveRequestDto(
        Long categoryId,
        String itemNm,
        int price,
        ItemSellStatus itemSellStatus,
        List<ItemImg> itemImgs,
        List<ItemSize> itemSizes
) {

}

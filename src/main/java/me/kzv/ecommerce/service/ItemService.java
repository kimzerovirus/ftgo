package me.kzv.ecommerce.service;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.controller.dtos.ItemSaveRequestDto;
import me.kzv.ecommerce.controller.dtos.ItemUpdateRequestDto;
import me.kzv.ecommerce.domain.entity.Category;
import me.kzv.ecommerce.domain.entity.Item;
import me.kzv.ecommerce.domain.entity.ItemImg;
import me.kzv.ecommerce.domain.entity.ItemSize;
import me.kzv.ecommerce.repository.CategoryRepository;
import me.kzv.ecommerce.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

//    public Long saveNewItem(ItemSaveRequestDto dto){
//        Category category = categoryRepository.getReferenceById(dto.categoryId());
//        Item item = dto.toEntity(category);
//
//        for (ItemImg itemImg : dto.itemImgs()) {
//            // TODO fileService
//            item.addItemImg(itemImg);
//        }
//
//        for(ItemSize itemSize : dto.itemSizes()){
//            item.addItemSize(itemSize);
//        }
//
//        return itemRepository.save(item).getId();
//    }

    public void updateItem(ItemUpdateRequestDto dto) {
        Item item = itemRepository.getReferenceById(dto.itemId());
        Category category = categoryRepository.getReferenceById(dto.categoryId());

        // TODO fileService update
        item.update(dto.itemNm(), dto.price(), dto.itemSellStatus(), category, dto.itemImgs(), dto.itemSizes());
    }

    public List<Item> getNewItemList() {
        // 메인 화면에 보여줄 신상품 리스트

        return null;
    }
}

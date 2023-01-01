package me.kzv.ecommerce.service;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.controller.dtos.ItemSaveRequestDto;
import me.kzv.ecommerce.controller.dtos.ItemUpdateRequestDto;
import me.kzv.ecommerce.domain.entity.Category;
import me.kzv.ecommerce.domain.entity.Item;
import me.kzv.ecommerce.domain.entity.ItemImg;
import me.kzv.ecommerce.domain.entity.ItemSize;
import me.kzv.ecommerce.domain.enums.ItemSellStatus;
import me.kzv.ecommerce.repository.CategoryRepository;
import me.kzv.ecommerce.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// dtoToEntity presentation vs service
// https://tecoble.techcourse.co.kr/post/2021-04-25-dto-layer-scope/
@Transactional
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemImgService itemImgService;

    public Long saveNewItem(ItemSaveRequestDto dto, List<MultipartFile> files) throws Exception {
        Category category = categoryRepository.findById(dto.categoryId()).orElseThrow(EntityExistsException::new);
        Item item = Item.of(dto.itemNm(), dto.price(), dto.itemSellStatus(), category);

        for (int i = 0; i < files.size(); i++) {
            ItemImg itemImg = itemImgService.saveItemImg(dto.itemImgs().get(i), files.get(i));
            item.addItemImg(itemImg);
        }

        for (ItemSize itemSize : dto.itemSizes()) {
            item.addItemSize(itemSize);
        }

        return itemRepository.save(item).getId();
    }

    public void updateItem(ItemUpdateRequestDto dto, List<MultipartFile> files) {
        Category category = categoryRepository.findById(dto.categoryId()).orElseThrow(EntityExistsException::new);
        Item item = itemRepository.findById(dto.itemId()).orElseThrow(EntityExistsException::new);
        List<ItemImg> itemImgs = null;
        List<ItemSize> itemSizes = null;

        // TODO fileService update - dto 로 받아올 때 update인지 delete 인지 flag
        item.update(dto.itemNm(), dto.price(), dto.itemSellStatus(), category, itemImgs, itemSizes);
    }

    public List<Item> getNewItemList() {
        // 메인 화면에 보여줄 신상품 리스트
        // select i.*, count(*) from item i, order_item o where i.id = o.item_id group by i.id order by count(*) desc

        return null;
    }
}

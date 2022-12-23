package me.kzv.ecommerce.service;

import me.kzv.ecommerce.controller.dtos.ItemSaveRequestDto;
import me.kzv.ecommerce.domain.entity.Category;
import me.kzv.ecommerce.domain.entity.Item;
import me.kzv.ecommerce.domain.entity.ItemImg;
import me.kzv.ecommerce.domain.entity.ItemSize;
import me.kzv.ecommerce.domain.enums.ItemSellStatus;
import me.kzv.ecommerce.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {
    @Autowired
    ItemService itemService;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void 상품_저장하기() throws Exception {
        //given
        Long categoryId = categoryRepository.save(Category.of("카테고리", null,1)).getId();

        ArrayList<ItemImg> itemImgs = new ArrayList<>();
        itemImgs.add(ItemImg.of("20221222image1","image1","https://www.naver.com",true));
        itemImgs.add(ItemImg.of("20221222image2","image2","https://www.naver.com",false));
        ArrayList<ItemSize> itemSizes = new ArrayList<>();

        ItemSaveRequestDto dto = new ItemSaveRequestDto(categoryId, "상품1", 0, ItemSellStatus.SELL, itemImgs, itemSizes);

        //when

        //then
        itemService.saveNewItem(dto);
        /**
         * Hibernate:
         *     insert
         *     into
         *         category
         *         (id, category_nm, created_at, created_by, modified_at, modified_by, parent_id)
         *     values
         *         (default, ?, ?, ?, ?, ?, ?)
         * Hibernate:
         *     insert
         *     into
         *         item
         *         (id, category_id, created_at, created_by, item_nm, item_sell_status, modified_at, modified_by, price)
         *     values
         *         (default, ?, ?, ?, ?, ?, ?, ?, ?)
         * Hibernate:
         *     insert
         *     into
         *         item_img
         *         (id, created_at, created_by, img_nm, img_url, item_id, modified_at, modified_by, origin_img_nm, rep_img_yn)
         *     values
         *         (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)
         * Hibernate:
         *     insert
         *     into
         *         item_img
         *         (id, created_at, created_by, img_nm, img_url, item_id, modified_at, modified_by, origin_img_nm, rep_img_yn)
         *     values
         *         (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)
         */
        // TODO cascade 를 활요하여 저장하려고 했더니 아이템 이미지가 저장될 때 하나씩 insert 됨
        // https://www.baeldung.com/spring-data-save-saveall
        // https://cheese10yun.github.io/jpa-batch-insert/
    }
}
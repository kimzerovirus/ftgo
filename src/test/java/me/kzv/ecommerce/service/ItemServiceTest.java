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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {
    @Autowired
    ItemService itemService;
    @Autowired
    CategoryRepository categoryRepository;

    List<MultipartFile> createFiles() throws Exception {
        List<MultipartFile> multipartFileList = new ArrayList<>();

        IntStream.rangeClosed(1, 3).forEach(i -> {
            String path = "/users/zerovirus/desktop/code/e-commerce/item/";
            String imageName = "test.jpg";
            MockMultipartFile multipartFile = null;
            try {
                multipartFile = new MockMultipartFile(path, imageName, "image/jpg", new FileInputStream(path+"/test/test.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            multipartFileList.add(multipartFile);
        });

        return multipartFileList;
    }

    @Test
    public void 상품_저장하기() throws Exception {
        //given
        Long categoryId = categoryRepository.save(Category.of("카테고리", null, 1)).getId();

        ArrayList<ItemImg> itemImgs = new ArrayList<>();
        itemImgs.add(ItemImg.of("20221222image1"));
        itemImgs.add(ItemImg.of("20221222image2"));
        itemImgs.add(ItemImg.of("20221222image3"));
        ArrayList<ItemSize> itemSizes = new ArrayList<>();

        ItemSaveRequestDto dto = new ItemSaveRequestDto(categoryId, "상품1", 0, ItemSellStatus.SELL, itemImgs, itemSizes);

        //when

        //then
        itemService.saveNewItem(dto, createFiles());
        /**
         * Hibernate:
         *     insert
         *     into
         *         category
         *         (id, category_nm, created_at, created_by, is_visible, modified_at, modified_by, order_id, parent_id)
         *     values
         *         (default, ?, ?, ?, ?, ?, ?, ?, ?)
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
         *         (id, created_at, created_by, img_nm, img_url, is_rep_img, item_id, modified_at, modified_by, origin_img_nm)
         *     values
         *         (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)
         * Hibernate:
         *     insert
         *     into
         *         item_img
         *         (id, created_at, created_by, img_nm, img_url, is_rep_img, item_id, modified_at, modified_by, origin_img_nm)
         *     values
         *         (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)
         * Hibernate:
         *     insert
         *     into
         *         item_img
         *         (id, created_at, created_by, img_nm, img_url, is_rep_img, item_id, modified_at, modified_by, origin_img_nm)
         *     values
         *         (default, ?, ?, ?, ?, ?, ?, ?, ?, ?)
         */

        // cascade 옵션으로 인해 item 만 저장하였지만 item_img 도 연달아 저장되는 것을 확인할 수 있다.
        // TODO cascade 를 활요하여 저장하려고 했더니 아이템 이미지가 저장될 때 하나씩 insert 됨
        // https://www.baeldung.com/spring-data-save-saveall
        // https://cheese10yun.github.io/jpa-batch-insert/
    }
}
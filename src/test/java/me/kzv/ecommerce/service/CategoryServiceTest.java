package me.kzv.ecommerce.service;

import me.kzv.ecommerce.controller.dtos.CategorySaveRequestDto;
import me.kzv.ecommerce.domain.entity.Category;
import me.kzv.ecommerce.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void 카테고리_생성() throws Exception {
        //given
        Category category = Category.of("바지", null, 1);

        //when
        Long result = categoryService.saveNewCategory(category);

        //then
        assertThat(result).isEqualTo(categoryRepository.findById(result).get().getId());
    }

    @Test
    public void 카테고리리스트() throws Exception {
        //given
        Category category1 = Category.of("바지", 0l, 1);
        Category category2 = Category.of("바지", null, 1);
        //when

        //then
    }

}
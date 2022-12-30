package me.kzv.ecommerce.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import me.kzv.ecommerce.controller.dtos.CategoryListDto;
import me.kzv.ecommerce.domain.entity.Category;
import me.kzv.ecommerce.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @PersistenceContext
    EntityManager em;

    public Category createCategory(String categoryNm){
        return categoryRepository.save(Category.of(categoryNm, -1l, 1));
    }

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
    public void 카테고리리스트_visible_캐싱() throws Exception {
        //given
        createCategory("상의");
        createCategory("하의");

        //when
        long now = System.currentTimeMillis();
        List<Category> categoryList = categoryService.getCategoryListByVisible();
        System.out.println("수행시간: " + (System.currentTimeMillis() - now));

        //then
        assertThat(categoryList.size()).isEqualTo(2);
    }

    @Test
    public void 카테고리리스트_visible() throws Exception {
        //given
        createCategory("상의");
        createCategory("하의");

        //when
        long now = System.currentTimeMillis();
        List<Category> categoryList = categoryService.getCategories();
        System.out.println("수행시간: " + (System.currentTimeMillis() - now));

        //then
        assertThat(categoryList.size()).isEqualTo(2);
    }

}
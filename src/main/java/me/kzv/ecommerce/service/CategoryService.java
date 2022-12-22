package me.kzv.ecommerce.service;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.controller.dtos.CategorySaveRequestDto;
import me.kzv.ecommerce.domain.entity.Category;
import me.kzv.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long saveNewCategory(Category category){
        return categoryRepository.save(category).getId();
    }

    @Transactional
    public void editCategory(Long categoryId, String categoryNm, Long parentId) {
        Category category = categoryRepository.getReferenceById(categoryId);
        category.update(categoryNm, parentId);
    }

    // TODO 카테고리 삭제 -> 카테고리를 fk 로 갖는 상품이 있으면 삭제는 안되고 감추기만 가능하다.
    // TODO 카테고리 명으로 조회

    @Transactional(readOnly = true)
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

}

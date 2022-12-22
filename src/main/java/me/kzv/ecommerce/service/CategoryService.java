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

    @Transactional(readOnly = true)
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

}

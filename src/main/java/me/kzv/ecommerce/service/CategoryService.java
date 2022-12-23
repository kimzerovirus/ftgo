package me.kzv.ecommerce.service;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.controller.dtos.CategoryDto;
import me.kzv.ecommerce.controller.dtos.CategorySaveRequestDto;
import me.kzv.ecommerce.domain.entity.Category;
import me.kzv.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Long saveNewCategory(Category category) {
        return categoryRepository.save(category).getId();
    }

    public void editCategory(List<Category> categoryList) {
        categoryRepository.saveAll(categoryList);
    }

    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.getReferenceById(categoryId);
        // TODO 카테고리 삭제 -> 카테고리를 fk 로 갖는 상품이 있으면 삭제는 안되고 감추기만 가능하다.
        categoryRepository.delete(category);
    }

    // TODO 카테고리 명으로 조회
    public void searchByCategoryNm(String categoryNm) {

    }

    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Map<Long, List<CategoryDto>> getCategoryList() {
         return getAllCategories().stream()
                .filter(Category::isVisible)
                .map(category -> new CategoryDto(category.getId(), category.getCategoryNm(), category.getParentId(), category.getOrderId()))
                .collect(Collectors.groupingBy(CategoryDto::categoryId));
    }

}

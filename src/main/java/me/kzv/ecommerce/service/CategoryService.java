package me.kzv.ecommerce.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.controller.dtos.CategoryDto;
import me.kzv.ecommerce.controller.dtos.CategoryListDto;
import me.kzv.ecommerce.domain.entity.Category;
import me.kzv.ecommerce.repository.CategoryCacheRepository;
import me.kzv.ecommerce.repository.CategoryRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryCacheRepository categoryCacheRepository;

    @Transactional
    public Long saveNewCategory(Category category) {
        return categoryRepository.save(category).getId();
    }

    @Transactional
    public void editCategory(List<Category> categoryList) {
        categoryRepository.saveAll(categoryList);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
//        Category category = categoryRepository.getReferenceById(categoryId);
        Category category = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
        // TODO 카테고리 삭제 -> 카테고리를 fk 로 갖는 상품이 있으면 삭제는 안되고 감추기만 가능하다. -> edit
        categoryRepository.delete(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // 전체 데이터를 가져와 visible 확인하는 방법에서 db 단에서 가져올때 visible = true 로 가져오는 걸로 변경
//    public Map<Long, List<CategoryDto>> getCategoryList() {
//         return getAllCategories().stream()
//                .filter(Category::isVisible)
//                .map(category -> new CategoryDto(category.getId(), category.getCategoryNm(), category.getParentId(), category.getOrderId()))
//                .collect(Collectors.groupingBy(CategoryDto::categoryId));
//    }


    // 유저 화면에 보여줄 카테고리
    public List<Category> getCategoryListByVisible() {
        return categoryCacheRepository.getCategoryList()
                .orElseGet(() -> categoryCacheRepository.save(categoryRepository.findByIsVisible(true)));
    }

//     @Cacheable("categoryList")
    public List<Category> getCategories(){
        return categoryRepository.findByIsVisible(true);
    }


}

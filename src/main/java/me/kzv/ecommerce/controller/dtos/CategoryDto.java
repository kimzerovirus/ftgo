package me.kzv.ecommerce.controller.dtos;

import me.kzv.ecommerce.domain.category.Category;

public record CategoryDto (
        Long categoryId,
        String categoryNm,
        Long parentId,
        int orderId,
        boolean isVisible,
        boolean isDel
){
    public static CategoryDto of(Category category) {
        return new CategoryDto(category.getId(), category.getCategoryNm(), category.getParentId(), category.getOrderId(), category.isVisible(), category.isDel());
    }
}


/**
 * category
 * 카테고리 아이디
 * 카테고리 명
 * 부모 아이디?
 * 보여주는 여부
 */
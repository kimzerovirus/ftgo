package me.kzv.ecommerce.controller.dtos;

import me.kzv.ecommerce.domain.entity.Category;

public record CategorySaveRequestDto(
        String categoryNm,
        Long parentId
) {
    public Category toEntity(){
        return Category.of(categoryNm, parentId);
    }
}

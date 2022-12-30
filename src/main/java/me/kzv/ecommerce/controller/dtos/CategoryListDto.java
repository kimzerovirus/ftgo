package me.kzv.ecommerce.controller.dtos;

import java.util.List;

public record CategoryListDto(
        List<CategoryDto> mainCategoryList,
        List<CategoryDto> subCategoryList
) {

}

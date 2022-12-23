package me.kzv.ecommerce.controller.dtos;

public record CategoryDto (
        Long categoryId,
        String categoryNm,
        Long parentId,
        int orderId
){

}


/**
 * category
 * 카테고리 아이디
 * 카테고리 명
 * 부모 아이디?
 * 보여주는 여부
 */
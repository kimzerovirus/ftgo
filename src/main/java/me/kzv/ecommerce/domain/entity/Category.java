package me.kzv.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryNm;
    private Long parentId;

    protected Category() {}

    private Category(String categoryNm, Long parentId) {
        this.categoryNm = categoryNm;
        this.parentId = parentId;
    }

    public static Category createCategory(String categoryNm, Long parentId) {
        return new Category(categoryNm, parentId);
    }
}

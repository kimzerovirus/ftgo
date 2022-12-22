package me.kzv.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Category extends BaseEntity{
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

    public static Category of(String categoryNm, Long parentId) {
        return new Category(categoryNm, parentId);
    }

    public void update(String categoryNm, Long parentId) {
        this.categoryNm = categoryNm;
        this.parentId = parentId;
    }
}

package me.kzv.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import me.kzv.ecommerce.utils.BooleanToYNConverter;

@Entity
@Getter
public class Category extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryNm;
    private Long parentId; // null 이라면 root 가 되는 아이디
    private int orderId; // parentId 가 그룹을 나타낸다면 orderId 는 순서(위치)를 나타낸다.

    @Convert(converter = BooleanToYNConverter.class)
    private boolean isVisible;

    protected Category() {}

    private Category(String categoryNm, Long parentId, int orderId) {
        this.categoryNm = categoryNm;
        this.parentId = parentId;
        this.orderId = orderId;
        this.isVisible = true;
    }

    public static Category of(String categoryNm, Long parentId, int orderId) {
        return new Category(categoryNm, parentId, orderId);
    }

    public void update(String categoryNm, Long parentId, int orderId, Boolean isVisible) {
        this.categoryNm = categoryNm;
        this.parentId = parentId;
        this.orderId = orderId;
        this.isVisible = isVisible;
    }
}

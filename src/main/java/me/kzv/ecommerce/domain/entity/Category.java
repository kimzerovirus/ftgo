package me.kzv.ecommerce.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import me.kzv.ecommerce.utils.BooleanToYNConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryNm;
    private Long parentId; // 값이 음수라면 root 가 되는 아이디
    private int orderId; // parentId 가 그룹을 나타낸다면 orderId 는 순서(위치)를 나타낸다.

//    @JsonProperty(value = "visible") // https://stackoverflow.com/questions/32270422/jackson-renames-primitive-boolean-field-by-removing-is
    @JsonIgnore
    @Convert(converter = BooleanToYNConverter.class)
    private boolean isVisible;

    @JsonIgnore
    @OneToMany(mappedBy = "category") // cascade 옵션을 사용하지 않음 merge
    private List<Item> items = new ArrayList<>(); // items 의 사이즈가 0일 경우에만 삭제가 가능하다

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

    @JsonIgnore
    public boolean isDel() {
        return items.size() > 0;
    }
}

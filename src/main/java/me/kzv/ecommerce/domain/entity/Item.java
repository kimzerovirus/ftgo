package me.kzv.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import me.kzv.ecommerce.domain.enums.ItemSellStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemNm; // 상품명
    private int price; // 상품 가격

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private final List<ItemSize> itemSizes = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private final List<ItemImg> itemImgs = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    protected Item() {}
}

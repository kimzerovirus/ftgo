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

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemSize> itemSizes = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemImg> itemImgs = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    protected Item() {}

    private Item(String itemNm, int price, ItemSellStatus itemSellStatus, Category category) {
        this.itemNm = itemNm;
        this.price = price;
        this.itemSellStatus = itemSellStatus;
        this.category = category;
    }

    //==생성 메서드==//
    public static Item of(String itemNm, int price, ItemSellStatus itemSellStatus, Category category) {
        return new Item(itemNm, price, itemSellStatus, category);
    }

    //==양방향 연관관계를 위한 메서드==/
    public void addItemSize(ItemSize itemSize) {
        itemSizes.add(itemSize);
        itemSize.setItem(this);
    }

    public void addItemImg(ItemImg itemImg) {
        itemImgs.add(itemImg);
        itemImg.setItem(this);
    }

    //==비즈니스 로직==//
    public void update(String itemNm, int price, ItemSellStatus itemSellStatus, Category category, List<ItemImg> itemImgs, List<ItemSize> itemSizes) {
        this.itemNm = itemNm;
        this.price = price;
        this.itemSellStatus = itemSellStatus;
        this.category = category; // 단방향 관계 - ManyToOne
        this.itemImgs = itemImgs;
        this.itemSizes = itemSizes;
    }



}

package me.kzv.ecommerce.domain.entity;

import jakarta.persistence.*;

@Entity
public class ItemSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sizeNm; // 사이즈 명 L, M, S 또는 44 55 66?
    private int stockNumber; // 재고 수량

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    protected ItemSize() {}

    //==양방향 연관관계를 위한 메서드==/
    public void setItem(Item item) {
        this.item = item;
    }
}

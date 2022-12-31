package me.kzv.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import me.kzv.ecommerce.exception.NotEnoughStockException;

@Getter
@Entity
public class ItemSize extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sizeNm; // 사이즈 명 L, M, S 또는 44 55 66?
    private int restStock; // 재고 수량

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    protected ItemSize() {}

    //==양방향 연관관계를 위한 메서드==/
    public void setItem(Item item) {
        this.item = item;
    }

    public void removeStock(int count) {
        int restStock = this.restStock - count;
        if (restStock < 0) {
            throw new NotEnoughStockException("재고가 부족합니다.");
        }
        this.restStock = restStock;
    }

    public void addStock(int count) {
        restStock += count;
    }

}

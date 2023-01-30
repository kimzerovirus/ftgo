package me.kzv.ecommerce.domain.cartitem;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import me.kzv.ecommerce.domain.cart.Cart;
import me.kzv.ecommerce.domain.BaseEntity;
import me.kzv.ecommerce.domain.item.Item;

@Entity
@Getter
public class CartItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count; // 주문 수량

    protected CartItem() {
    }

    @Builder
    public CartItem(Long id, Cart cart, Item item, int count) {
        this.id = id;
        this.cart = cart;
        this.item = item;
        this.count = count;
    }
}

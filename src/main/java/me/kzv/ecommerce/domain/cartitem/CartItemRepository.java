package me.kzv.ecommerce.domain.cartitem;

import me.kzv.ecommerce.domain.cartitem.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

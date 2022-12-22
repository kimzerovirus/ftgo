package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.domain.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

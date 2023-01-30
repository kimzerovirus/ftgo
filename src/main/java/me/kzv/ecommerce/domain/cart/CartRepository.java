package me.kzv.ecommerce.domain.cart;

import me.kzv.ecommerce.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

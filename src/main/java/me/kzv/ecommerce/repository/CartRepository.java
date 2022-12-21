package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

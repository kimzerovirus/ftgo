package me.kzv.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItem extends JpaRepository<CartItem, Long> {
}

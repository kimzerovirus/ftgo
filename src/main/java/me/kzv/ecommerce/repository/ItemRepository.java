package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

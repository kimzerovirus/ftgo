package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.domain.entity.ItemSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSizeRepository extends JpaRepository<ItemSize, Long> {
}

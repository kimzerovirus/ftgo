package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.domain.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
}

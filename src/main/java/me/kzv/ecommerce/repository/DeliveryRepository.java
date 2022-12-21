package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.domain.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}

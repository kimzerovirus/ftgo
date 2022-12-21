package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

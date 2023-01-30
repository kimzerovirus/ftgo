package me.kzv.ecommerce.domain.review;

import me.kzv.ecommerce.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}

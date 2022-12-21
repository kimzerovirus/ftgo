package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.domain.entity.ReviewReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewReplyRepository extends JpaRepository<ReviewReply, Long> {
}

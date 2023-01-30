package me.kzv.ecommerce.domain.reviewreply;

import jakarta.persistence.*;
import lombok.Getter;
import me.kzv.ecommerce.domain.BaseEntity;
import me.kzv.ecommerce.domain.review.Review;

@Entity
@Getter
public class ReviewReply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    protected ReviewReply() {
    }
}

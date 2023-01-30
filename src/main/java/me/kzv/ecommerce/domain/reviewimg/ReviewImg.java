package me.kzv.ecommerce.domain.reviewimg;

import jakarta.persistence.*;
import lombok.Getter;
import me.kzv.ecommerce.domain.BaseEntity;
import me.kzv.ecommerce.domain.review.Review;

@Entity
@Getter
public class ReviewImg extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgNm;
    private String originImgNm;
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
}

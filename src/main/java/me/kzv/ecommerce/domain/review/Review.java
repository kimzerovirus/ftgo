package me.kzv.ecommerce.domain.review;

import jakarta.persistence.*;
import lombok.Getter;
import me.kzv.ecommerce.domain.BaseEntity;
import me.kzv.ecommerce.domain.item.Item;
import me.kzv.ecommerce.domain.orderitem.OrderItem;
import me.kzv.ecommerce.domain.member.Member;
import me.kzv.ecommerce.domain.reviewimg.ReviewImg;
import me.kzv.ecommerce.domain.reviewreply.ReviewReply;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Review  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @Lob
    private String contents; // 리뷰 내용

    private int score; // 별점

    @OneToMany(mappedBy = "review", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ReviewImg> reviewImgs = new ArrayList<>();

    @OneToMany(mappedBy = "review", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ReviewReply> reviewReplys = new ArrayList<>();

    protected Review() {}
}

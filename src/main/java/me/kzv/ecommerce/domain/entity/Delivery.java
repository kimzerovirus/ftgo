package me.kzv.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import me.kzv.ecommerce.domain.entity.member.Member;

@Getter
@Entity
public class Delivery { // 배송지 주소
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    private String baseAddress; // 기본 주소지
    private String detailAddress; // 상세 주소지
    private String zipcode; // 우편 번호

    private String deliveryName; // 주소 이름
    private Boolean isRecent; // 최근 배송지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected Delivery(){}

    @Builder
    public Delivery(Long id, String baseAddress, String detailAddress, String zipcode, String deliveryName, Boolean isRecent, Member member) {
        this.id = id;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.zipcode = zipcode;
        this.deliveryName = deliveryName;
        this.isRecent = isRecent;
        this.member = member;
    }
}
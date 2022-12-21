package me.kzv.ecommerce.domain.entity.member;

import jakarta.persistence.*;
import lombok.Getter;
import me.kzv.ecommerce.domain.entity.BaseTimeEntity;
import me.kzv.ecommerce.domain.enums.AuthorityType;
import me.kzv.ecommerce.domain.enums.GradeType;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private Boolean isVerifiedEmail; // TODO 로컬 가입시 -> 이메일 인증 필요

    private int point; // 적립금

    @Enumerated(EnumType.STRING)
    private AuthorityType authorityType; // 회원 권한

    @Enumerated(EnumType.STRING)
    private GradeType gradeType; // 회원 등급

    protected Member() {}
}
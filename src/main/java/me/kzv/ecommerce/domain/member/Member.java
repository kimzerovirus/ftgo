package me.kzv.ecommerce.domain.member;

import jakarta.persistence.*;
import lombok.Getter;
import me.kzv.ecommerce.domain.BaseTimeEntity;
import me.kzv.ecommerce.domain.member.enums.AuthorityType;
import me.kzv.ecommerce.domain.member.enums.GradeType;
import me.kzv.ecommerce.utils.BooleanToYNConverter;

import java.time.LocalDate;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "login_type")
public abstract class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    private LocalDate birthday;

    private int mileage; // 적립금

    @Enumerated(EnumType.STRING)
    private AuthorityType authorityType; // 회원 권한

    @Enumerated(EnumType.STRING)
    private GradeType gradeType; // 회원 등급

    protected Member() {}

    protected Member(String username, String email, LocalDate birthday) {
        this.username = username;
        this.email = email;
        this.birthday = birthday;
        this.mileage = 1000; // 가입시 1000 포인트 기본 제공
        this.authorityType = AuthorityType.USER;
        this.gradeType = GradeType.BRONZE;
    }
}

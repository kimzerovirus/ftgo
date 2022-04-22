package me.kzv.crud.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tbl_memo")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}


/*
    @Builder를 이용하려면
    @AllArgsConstructor
    @NoArgsConstructor
    위 2개가 세트로 와야됨
*/
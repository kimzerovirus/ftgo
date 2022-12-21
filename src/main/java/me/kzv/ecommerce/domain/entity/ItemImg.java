package me.kzv.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ItemImg extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgNm;
    private String originImgNm;
    private String imgUrl;
    private Boolean repImgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}

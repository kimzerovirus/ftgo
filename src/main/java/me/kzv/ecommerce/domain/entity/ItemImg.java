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

    protected ItemImg() {}

    private ItemImg(String imgNm, String originImgNm, String imgUrl, Boolean repImgYn) {
        this.imgNm = imgNm;
        this.originImgNm = originImgNm;
        this.imgUrl = imgUrl;
        this.repImgYn = repImgYn;
    }

    //==생성 메서드==//
    public static ItemImg of(String imgNm, String originImgNm, String imgUrl, Boolean repImgYn){
        return new ItemImg(imgNm, originImgNm, imgUrl, repImgYn);
    }

    //==양방향 연관관계를 위한 메서드==/
    public void setItem(Item item) {
        this.item = item;
    }
}

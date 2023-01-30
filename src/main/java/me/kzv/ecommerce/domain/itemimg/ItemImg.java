package me.kzv.ecommerce.domain.itemimg;

import jakarta.persistence.*;
import lombok.Getter;
import me.kzv.ecommerce.domain.BaseEntity;
import me.kzv.ecommerce.domain.item.Item;
import me.kzv.ecommerce.utils.BooleanToYNConverter;

@Entity
@Getter
public class ItemImg extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgNm;
    private String originImgNm;
    private String imgUrl;
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isRepImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    protected ItemImg() {
    }

    private ItemImg(String originImgNm) {
        this.originImgNm = originImgNm;
    }

    //==생성 메서드==//
    public static ItemImg of(String originImgNm) {
        return new ItemImg(originImgNm);
    }

    //==양방향 연관관계를 위한 메서드==/
    public void setItem(Item item) {
        this.item = item;
    }

    public void update(String imgNm, String imgUrl, Boolean isRepImg) {
        this.imgNm = imgNm;
        this.imgUrl = imgUrl;
        this.isRepImg = isRepImg;
    }

}

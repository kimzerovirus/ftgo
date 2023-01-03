package me.kzv.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import me.kzv.ecommerce.domain.enums.OrderStatus;

@Entity
@Getter
public class OrderItem  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 단방향
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY) // 양방향
    @JoinColumn(name = "order_id")
    private Order order;

    private int count; // 수량

    private String size; // 사이즈 명

    private int orderPrice; // 결제 금액 - 회원의 등급에 따라 가격이 차등 결정됨

    private Long reviewId; // 리뷰 아이디만 저장

    protected OrderItem() {}

    private OrderItem(Item item, int orderPrice, int count, String size) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
        this.size = size;
    }

    //== 생성 메서드==//
    public static OrderItem of(Item item, int orderPrice ,int count, String size) {
        for (ItemSize itemSize : item.getItemSizes()) {
            if (itemSize.getSizeNm().equals(size)) itemSize.removeStock(count);
        }
        return new OrderItem(item, orderPrice, count, size);
    }

    //== 연관관계를 위한 메서드==//
    public void setOrder(Order order) {
        this.order = order;
    }

    public int getTotalOrderPrice(){
        return orderPrice * count;
    }

    public void cancel(){
        for (ItemSize itemSize : getItem().getItemSizes()) {
            if(itemSize.getSizeNm().equals(size)) itemSize.addStock(count);
        }
    }

    public boolean isEnableReview(){ // 리뷰 작성이 가능한지
        return order.getOrderStatus() == OrderStatus.FINISHED && reviewId == null;
    }


}

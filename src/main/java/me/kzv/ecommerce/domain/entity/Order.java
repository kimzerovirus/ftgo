package me.kzv.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import me.kzv.ecommerce.domain.entity.member.Member;
import me.kzv.ecommerce.domain.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

//    @Enumerated(EnumType.STRING) // ordinal 은 숫자라서 나중에 테이블 변경이 있다면 숫자가 밀려서 기존데이터와의 혼동이 올 수 있다.
//    private DeliveryStatus status; // [READY, COMP]

    // deliverystatus => orderstatus 로 변경
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    protected Order() {}

    private Order(Member member){
        this.member = member;
    }

    public static Order of(Member member, List<OrderItem> orderItems){
        var order = new Order(member);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        return order;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public int getTotalPrice(){
        var totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalOrderPrice();
        }
        return totalPrice;
    }

    public void cancelOrder(){
        orderStatus = OrderStatus.CANCEL;
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
}

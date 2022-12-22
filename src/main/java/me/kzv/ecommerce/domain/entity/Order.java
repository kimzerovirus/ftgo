package me.kzv.ecommerce.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import me.kzv.ecommerce.domain.enums.DeliveryStatus;

@Entity
@Getter
@Table(name = "orders")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // ordinal 은 숫자라서 나중에 테이블 변경이 있다면 숫자가 밀려서 기존데이터와의 혼동이 올 수 있다.
    private DeliveryStatus status; // [READY, COMP]

    protected Order() {}
}

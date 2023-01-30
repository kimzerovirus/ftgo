package me.kzv.ecommerce.domain.coupon;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import me.kzv.ecommerce.domain.BaseEntity;

@Entity
@Getter
public class Coupon  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected Coupon() {}
}

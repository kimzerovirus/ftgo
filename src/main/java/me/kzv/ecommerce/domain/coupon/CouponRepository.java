package me.kzv.ecommerce.domain.coupon;

import me.kzv.ecommerce.domain.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}

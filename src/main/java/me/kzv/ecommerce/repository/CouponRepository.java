package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.domain.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}

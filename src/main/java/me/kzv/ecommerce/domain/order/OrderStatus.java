package me.kzv.ecommerce.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    READY("상품 준비중"),
    SHIPPING("배송중"),
    FINISHED("배송 완료"),
    CANCEL("주문 취소"),
    ;

    private final String value;
}

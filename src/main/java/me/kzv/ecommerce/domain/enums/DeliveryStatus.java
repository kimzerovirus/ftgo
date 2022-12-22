package me.kzv.ecommerce.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryStatus {
    READY("배송 준비중"),
    SHIPPING("배송중"),
    FINISHED("배송 완료"),
    ;

    private final String value;
}

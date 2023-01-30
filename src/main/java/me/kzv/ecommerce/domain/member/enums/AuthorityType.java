package me.kzv.ecommerce.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthorityType {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private final String value;
}

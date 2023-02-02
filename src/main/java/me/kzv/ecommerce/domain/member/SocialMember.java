package me.kzv.ecommerce.domain.member;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import me.kzv.ecommerce.domain.member.enums.SocialType;

@Entity
@Getter
@DiscriminatorValue("SOCIAL")
public class SocialMember extends Member {
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    protected SocialMember() {}

    private SocialMember(String name, String email, SocialType socialType) {
        super(name, email, true);
        this.socialType = socialType;
    }


}

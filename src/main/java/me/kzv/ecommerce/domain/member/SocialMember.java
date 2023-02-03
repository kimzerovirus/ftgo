package me.kzv.ecommerce.domain.member;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import me.kzv.ecommerce.domain.member.enums.SocialType;

import java.time.LocalDate;

@Entity
@Getter
@DiscriminatorValue("SOCIAL")
public class SocialMember extends Member {
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    protected SocialMember() {}

    private SocialMember(String username, String email, LocalDate birthday, SocialType socialType) {
        super(username, email, birthday);
        this.socialType = socialType;
    }


}

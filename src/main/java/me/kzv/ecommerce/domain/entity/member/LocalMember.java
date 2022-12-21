package me.kzv.ecommerce.domain.entity.member;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("LOCAL")
public class LocalMember extends Member{
    private String password;
}

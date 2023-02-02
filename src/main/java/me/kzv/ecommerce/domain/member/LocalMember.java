package me.kzv.ecommerce.domain.member;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@DiscriminatorValue("LOCAL")
public class LocalMember extends Member {
    private String password;

    protected LocalMember() {
    }

    private LocalMember(String name, String email, String password) {
        super(name, email, false);
        this.password = password;
    }

    public static Member of(String name, String email, String password, PasswordEncoder passwordEncoder) {
        return new LocalMember(name, email, passwordEncoder.encode(password));
    }
}

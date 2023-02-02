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

    private LocalMember(String username, String email, String password) {
        super(username, email, false);
        this.password = password;
    }

    public static Member of(String username, String email, String password, PasswordEncoder passwordEncoder) {
        return new LocalMember(username, email, passwordEncoder.encode(password));
    }
}

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

    private LocalMember(String email, String password) {
        super(email, false);
        this.password = password;
    }

    public static LocalMember of(String email, String password, PasswordEncoder passwordEncoder) {
        return new LocalMember(email, passwordEncoder.encode(password));
    }
}

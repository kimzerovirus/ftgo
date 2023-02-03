package me.kzv.ecommerce.domain.member;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@DiscriminatorValue("LOCAL")
public class LocalMember extends Member {
    private String password;

    protected LocalMember() {
    }

    private LocalMember(String username, String email, LocalDate birthday, String password) {
        super(username, email, birthday);
        this.password = password;
    }

    public static Member of(String username, String email, String password, String birthday, PasswordEncoder passwordEncoder) {
        LocalDate date = LocalDate.parse(birthday, DateTimeFormatter.ISO_DATE);
        return new LocalMember(username, email, date, passwordEncoder.encode(password));
    }
}

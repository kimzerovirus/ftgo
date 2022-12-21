package me.kzv.ecommerce.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO SecurityContextHolder.getContext
        return Optional.of("테스트 유저");
    }
}

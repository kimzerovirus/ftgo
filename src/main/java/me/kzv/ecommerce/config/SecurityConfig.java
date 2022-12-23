package me.kzv.ecommerce.config;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.security.CustomAuthenticationProvider;
import me.kzv.ecommerce.security.CustomUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .anyRequest().permitAll()
                )
                // TODO h2-console 사용하기 위해 임시로 풀어둠
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()

                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)

                .and()
                .userDetailsService(customUserDetailsService)
                .authenticationProvider(customAuthenticationProvider)
        ;
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 보안필터를 거치지 않는다.
        // 정적 파일 설정 /js /css /images
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

}

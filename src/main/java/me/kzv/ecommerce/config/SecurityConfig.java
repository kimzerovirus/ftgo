package me.kzv.ecommerce.config;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.security.CustomAccessDeniedHandler;
import me.kzv.ecommerce.security.local.LocalAuthenticationFailureHandler;
import me.kzv.ecommerce.security.local.LocalAuthenticationProvider;
import me.kzv.ecommerce.security.local.LocalAuthenticationSuccessHandler;
import me.kzv.ecommerce.security.local.LocalUserDetailsService;
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

    private final LocalUserDetailsService localUserDetailsService;
    private final LocalAuthenticationProvider localAuthenticationProvider;
    private final LocalAuthenticationSuccessHandler localAuthenticationSuccessHandler;
    private final LocalAuthenticationFailureHandler localAuthenticationFailureHandler;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers("/my-page").hasRole("USER")
                                .anyRequest().permitAll()
                )
                // TODO h2-console 사용하기 위해 임시로 풀어둠
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()

                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login-proc")
                .defaultSuccessUrl("/", true)
                .successHandler(localAuthenticationSuccessHandler)
                .failureHandler(localAuthenticationFailureHandler)
                .permitAll()

                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)

                .and()
                .userDetailsService(localUserDetailsService)
                .authenticationProvider(localAuthenticationProvider)

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

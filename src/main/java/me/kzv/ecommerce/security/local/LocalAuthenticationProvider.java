package me.kzv.ecommerce.security.local;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.security.MemberSecurityContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalAuthenticationProvider implements AuthenticationProvider {

    private final LocalUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials(); // 입력 받은 비밀번호

        MemberSecurityContext userDetails = (MemberSecurityContext) userDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Email or Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Local 로그인의 경우에만 authenticationProvider 가 동작해야 된다.
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

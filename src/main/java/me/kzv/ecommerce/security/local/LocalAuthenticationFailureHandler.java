package me.kzv.ecommerce.security.local;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class LocalAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errMsg = "유효하지 않은 요청입니다.";
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            errMsg = "이메일 또는 비밀번호를 확인해주세요";
        }

        String url = UriComponentsBuilder.fromUriString("/login")
                .queryParam("error", "true")
                .queryParam("exception", URLEncoder.encode(errMsg, StandardCharsets.UTF_8))
                .build().toString();
        setDefaultFailureUrl(url);

        super.onAuthenticationFailure(request, response, exception);
    }
}

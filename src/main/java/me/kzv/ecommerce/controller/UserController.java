package me.kzv.ecommerce.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.controller.dtos.LocalMemberRequestDto;
import me.kzv.ecommerce.domain.member.LocalMember;
import me.kzv.ecommerce.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/join")
    public String join() { // 소셜 / 스토어 회원가입 선택 창
        return "user/join";
    }

    @GetMapping("/create-account")
    public String register() { // 스토어 회원 정보 기입 창
        return "user/joinForm";
    }

    @PostMapping("/create-account")
    public String createAccount(@Valid LocalMemberRequestDto dto, BindingResult bindingResult, Model model) { // 계정 생성
        if(bindingResult.hasErrors()) { // 유효성 검사
            return "user/joinForm";
        }

        try {
            memberService.createLocalMember(dto.username(), dto.email(), dto.password());
        } catch (IllegalStateException e) { // 중복 회원 예외 처리
            model.addAttribute("errMsg", e.getMessage());
            return "user/joinForm";
        }

        model.addAttribute("msg", "회원가입을 환영합니다.");
        model.addAttribute("url", "/");
        return "common/alert";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }
}

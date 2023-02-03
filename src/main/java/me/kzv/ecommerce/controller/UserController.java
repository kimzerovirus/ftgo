package me.kzv.ecommerce.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.controller.dtos.LocalMemberRequestDto;
import me.kzv.ecommerce.controller.validators.LocalMemberRequestValidator;
import me.kzv.ecommerce.domain.member.Member;
import me.kzv.ecommerce.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final MemberService memberService;
    private final LocalMemberRequestValidator localMemberRequestValidator;

    @InitBinder("localMemberRequestDto")
    public void initBinder(WebDataBinder data) {
        data.addValidators(localMemberRequestValidator);
    }

    @GetMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "exception", required = false) String exception,
            Model model
    ) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "user/login";
    }

    @GetMapping("/join")
    public String join() { // 소셜 / 스토어 회원가입 선택 창
        return "user/join";
    }

    @GetMapping("/create-account")
    public String register(Model model) { // 스토어 회원 정보 기입 창
        model.addAttribute("localMemberRequestDto", new LocalMemberRequestDto(null, null, null, null));
        return "user/joinForm";
    }

    @PostMapping("/create-account")
    public String createAccount(@ModelAttribute("localMemberRequestDto") @Valid LocalMemberRequestDto dto, BindingResult bindingResult, Model model) { // 계정 생성
        if (bindingResult.hasErrors()) { // 유효성 검사
            return "user/joinForm";
        }

        memberService.createLocalMember(dto.username(), dto.email(), dto.password(), dto.birthday());

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

    @GetMapping(value = "/my-page")
    public String mypage(Authentication authentication, Model model){
        try {
            Member userInfo = memberService.getUserInfo(authentication.getName());
            model.addAttribute("username", userInfo.getUsername());
            model.addAttribute("grade", userInfo.getGradeType());
            model.addAttribute("mileage", userInfo.getMileage());
        } catch (EntityNotFoundException e) {
            model.addAttribute("msg", "존재하지 않는 회원입니다.");
            model.addAttribute("url", "/");
            return "common/alert";
        }
        return "user/userinfo";
    }
}

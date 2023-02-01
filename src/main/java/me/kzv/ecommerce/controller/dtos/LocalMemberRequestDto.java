package me.kzv.ecommerce.controller.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LocalMemberRequestDto(
        @NotBlank(message = "필수 정보입니다.")
        String name,

        @NotBlank(message = "필수 정보입니다.")
        @Email(message = "이메일만 입력 가능합니다.")
        String email,

        @NotBlank(message = "필수 정보입니다.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        String password
) {
}

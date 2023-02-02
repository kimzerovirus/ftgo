package me.kzv.ecommerce.controller.validators;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.controller.dtos.LocalMemberRequestDto;
import me.kzv.ecommerce.domain.member.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Component
public class LocalMemberRequestValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(LocalMemberRequestDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LocalMemberRequestDto dto = (LocalMemberRequestDto) target;

        if (memberRepository.existsByEmail(dto.email())) {
            errors.rejectValue("email", "invalid.email", new Object[]{dto.email()},"이미 사용중인 이메일입니다.");
        }

        boolean isValidBirthday = compareValidBirthday(dto.birthday());
        if (!isValidBirthday) {
            errors.rejectValue("birthday", "invalid.birthday", new Object[]{dto.birthday()}, "만 14세 이상 가입 가능합니다.");
        }
    }

    private boolean compareValidBirthday(String birthday){
        LocalDate standardDate = LocalDate.now().minusYears(14);
        LocalDate date = LocalDate.parse(birthday, DateTimeFormatter.ISO_DATE);
        return date.isBefore(standardDate);
    }
}

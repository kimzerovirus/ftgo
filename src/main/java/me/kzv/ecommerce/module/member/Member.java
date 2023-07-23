package me.kzv.ecommerce.module.member;

import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
public class Member {
    private Long idx;

    private String name;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    @Builder
    public Member(Long idx, String name, String email, String password, LocalDateTime createdAt) {
        Preconditions.checkArgument(isNotEmpty(name), requirementErrorMessage("이름"));
        Preconditions.checkArgument(
                Pattern.matches("^[ㄱ-ㅎ가-힣a-zA-Z]*$", name),
                "한글, 영문 대/소문자를 사용해주세요."
        );
        Preconditions.checkNotNull(email, requirementErrorMessage("이메일"));
        Preconditions.checkNotNull(password, requirementErrorMessage("비밀번호"));
        this.idx = idx;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = defaultIfNull(createdAt, LocalDateTime.now());
    }

    private String requirementErrorMessage(String target) {
        return target + "(은)는 필수 입력 값입니다.";
    }

    public void insertAutoIncrementedIdx(Long idx) {
        this.idx = idx;
    }

}
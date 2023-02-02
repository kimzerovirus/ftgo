package me.kzv.ecommerce.service;

import me.kzv.ecommerce.domain.member.LocalMember;
import me.kzv.ecommerce.domain.member.Member;
import me.kzv.ecommerce.exception.DuplicatedMemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @DisplayName("로컬 회원 가입 테스트")
    @Test
    public void createLocalMemberTest() {
        var name = "새로운 사람";
        var email = "email@email.com";
        var pwd = "12341234";
        LocalMember savedMember = (LocalMember) memberService.createLocalMember(name, email, pwd);

        assertEquals(name, savedMember.getName());
        assertEquals(email, savedMember.getEmail());
        assertTrue(passwordEncoder.matches(pwd, savedMember.getPassword()));
    }

    @DisplayName("로컬 회원 중복 에러 테스트")
    @Test
    public void duplicatedLocalMemberTest() throws Exception {
        //given
        var name = "새로운 사람";
        var email = "email@email.com";
        var pwd = "12341234";
        memberService.createLocalMember(name, email, pwd);

        //when
        var e = assertThrows(DuplicatedMemberException.class, () -> {
            memberService.createLocalMember(name, email, pwd);
        });

        //then
        assertEquals("이미 가입된 이메일입니다.", e.getMessage());
    }

}
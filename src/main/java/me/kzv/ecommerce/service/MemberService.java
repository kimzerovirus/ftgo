package me.kzv.ecommerce.service;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.domain.member.LocalMember;
import me.kzv.ecommerce.domain.member.Member;
import me.kzv.ecommerce.domain.member.MemberRepository;
import me.kzv.ecommerce.exception.DuplicatedMemberException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member createLocalMember(String name, String email, String password) {
        checkDuplicateEmail(email);
        Member member = LocalMember.of(name, email, password, passwordEncoder);
        return memberRepository.save(member);
    }

    private void checkDuplicateEmail(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isPresent()) {
            throw new DuplicatedMemberException("이미 가입된 이메일입니다.");
        }
    }
}

package me.kzv.ecommerce.service;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.domain.member.LocalMember;
import me.kzv.ecommerce.domain.member.Member;
import me.kzv.ecommerce.domain.member.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member register(String email, String password) {
        Member member = LocalMember.of(email, password, passwordEncoder);
        return memberRepository.save(member);
    }
}

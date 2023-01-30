package me.kzv.ecommerce.security;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.domain.member.Member;
import me.kzv.ecommerce.domain.member.SocialMember;
import me.kzv.ecommerce.domain.member.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("이메일 또는 비밀번호를 확인해주세요");
        });

        var isSocial = (SocialMember) member;
        if (isSocial.getSocialType() != null) { // 로컬 회원이 아닌 소셜로 가입한 회원
            throw new UsernameNotFoundException("이메일 또는 비밀번호를 확인해주세요");
        }


        return MemberSecurityContext.of(member);
    }

}

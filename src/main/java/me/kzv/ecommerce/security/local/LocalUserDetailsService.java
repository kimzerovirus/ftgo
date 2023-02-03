package me.kzv.ecommerce.security.local;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.domain.member.Member;
import me.kzv.ecommerce.domain.member.SocialMember;
import me.kzv.ecommerce.domain.member.MemberRepository;
import me.kzv.ecommerce.security.MemberSecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not exist");
        });

        return MemberSecurityContext.of(member);
    }

}

package me.kzv.crud.security.service

import me.kzv.crud.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository,
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val member = memberRepository.findByIdOrNull(email) ?: throw UsernameNotFoundException("유저가 존재하지 않습니다.")

        val grantedAuthority: MutableList<GrantedAuthority> = arrayListOf()
        member.authorities.forEach { authorityType -> grantedAuthority.add(SimpleGrantedAuthority(authorityType.toString())) }

        return MemberContext(member, grantedAuthority)
    }
}
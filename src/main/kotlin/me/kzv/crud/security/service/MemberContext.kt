package me.kzv.crud.security.service

import me.kzv.crud.domain.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

// 생성자로 받은 값을 super()에 바로 집어 넣기
class MemberContext(private val member: Member, authorities: Collection<GrantedAuthority>) :
    User(member.email, member.password, authorities) {
    fun getAccount(): Member = member
}
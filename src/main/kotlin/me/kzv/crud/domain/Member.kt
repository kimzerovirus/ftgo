package me.kzv.crud.domain

import me.kzv.crud.domain.enum.AuthorityType
import me.kzv.crud.domain.enum.LoginType
import javax.persistence.*

@Entity
class Member(
    @Id
    val email: String,
    val password: String?,
    val nickname: String,
    val loginType: LoginType,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY) // default value = user
    val authorities: List<AuthorityType> = arrayListOf(AuthorityType.ROLE_USER),
){
}
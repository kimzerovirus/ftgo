package me.kzv.crud.persistence

import me.kzv.crud.persistence.enum.LoginType
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Member(
    @Id
    val email: String,
    val password: String,
    val nickname: String,
    val loginType: LoginType,
){
}
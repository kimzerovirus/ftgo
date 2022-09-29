package me.kzv.crud.service

import me.kzv.crud.persistence.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    fun register(){}

    fun login(){}
}
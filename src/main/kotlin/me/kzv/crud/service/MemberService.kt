package me.kzv.crud.service

import me.kzv.crud.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    fun register(){}

    fun login(){}
}
package me.kzv.crud.repository

import me.kzv.crud.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, String> {

}
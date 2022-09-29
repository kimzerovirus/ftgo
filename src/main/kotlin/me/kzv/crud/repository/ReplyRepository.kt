package me.kzv.crud.repository

import me.kzv.crud.domain.Reply
import org.springframework.data.jpa.repository.JpaRepository

interface ReplyRepository : JpaRepository<Reply, String> {

}
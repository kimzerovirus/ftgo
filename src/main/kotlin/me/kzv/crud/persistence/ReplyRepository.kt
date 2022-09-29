package me.kzv.crud.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface ReplyRepository : JpaRepository<Reply, String> {

}
package me.kzv.crud.service

import me.kzv.crud.persistence.BoardRepository
import me.kzv.crud.persistence.ReplyRepository
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val boardRepository: BoardRepository,
    private val replyRepository: ReplyRepository,
) {
    fun register(){}

    fun getList(){}

    fun modify(){}

    fun remove(){}
}
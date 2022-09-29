package me.kzv.crud.service

import me.kzv.crud.repository.BoardRepository
import me.kzv.crud.repository.ReplyRepository
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
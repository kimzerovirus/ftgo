package me.kzv.crud.repository

import me.kzv.crud.domain.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long> {

}
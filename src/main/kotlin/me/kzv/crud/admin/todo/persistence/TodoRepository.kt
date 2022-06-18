package me.kzv.crud.admin.todo.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long>{

}

package me.kzv.crud.admin.todo.service

import me.kzv.crud.admin.todo.persistence.Todo
import me.kzv.crud.admin.todo.persistence.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository // nestjs사용할때랑 느낌이 매우 비슷한듯
){
    fun getAllTodos() = todoRepository.findAll()

    fun addTodo(todoName:String) = todoRepository.save(Todo(todoWork = todoName))

    fun updateTodo(idx: Long): Todo {
        val todo = todoRepository.findByIdOrNull(idx) ?: throw Exception()
        todo.completed = !todo.completed // true : false
        return todoRepository.save(todo)
    }

    fun deleteTodo(idx: Long) = todoRepository.deleteById(idx)
}
package me.kzv.crud.admin.todo

import me.kzv.crud.admin.todo.dto.TodoRequest
import me.kzv.crud.admin.todo.service.TodoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/todo")
class TodoController(
    private val todoService: TodoService
){
    @GetMapping
    fun getAllTodos() = todoService.getAllTodos()

    @PostMapping
    fun addTodo(@RequestBody todoRequest: TodoRequest) = todoService.addTodo(todoRequest.todoWork)

    @PutMapping("/{idx}")
    fun updateTodo(@PathVariable("idx") idx: Long) = todoService.updateTodo(idx)

    @DeleteMapping("/{idx}")
    fun deleteTodo(@PathVariable("idx") idx: Long) = todoService.deleteTodo(idx)
}
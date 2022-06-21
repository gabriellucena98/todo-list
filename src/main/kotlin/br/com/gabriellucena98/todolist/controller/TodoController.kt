package br.com.gabriellucena98.todolist.controller

import br.com.gabriellucena98.todolist.converter.TodoConverter
import br.com.gabriellucena98.todolist.dto.TodoDTO
import br.com.gabriellucena98.todolist.service.TodoService
import io.swagger.annotations.ApiResponse
import java.util.UUID
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todo")
class TodoController(
    private val todoService: TodoService
) {

    @GetMapping
    fun getAll(): List<TodoDTO>{
        return todoService.getAll().map { TodoConverter.toDTO(it) }
    }

    @PostMapping
    @ApiResponse(code = 200, message = "Adicionado!")
    fun createTodo(@RequestBody todo: TodoDTO): TodoDTO {
        return todoService.createTodo(TodoConverter.toDomain(todo)).let { TodoConverter.toDTO(it) }
    }

    @PutMapping("/{id}")
    fun editTodo(
        @PathVariable("id") id: UUID,
        @RequestBody todoDTO: TodoDTO): TodoDTO{
        val todo = TodoConverter.toDomain(todoDTO.copy(id = id))
        return TodoConverter.toDTO(todoService.editTodo(todo))
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable("id") id: UUID) {
        todoService.deleteTodo(id)

    }
}
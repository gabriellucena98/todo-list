package br.com.gabriellucena98.todolist.controller

import br.com.gabriellucena98.todolist.converter.TodoConverter
import br.com.gabriellucena98.todolist.dto.TodoDTO
import br.com.gabriellucena98.todolist.service.TodoService
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
    fun createTodo(@RequestBody json: String): TodoDTO {
        return TodoConverter.toDTO(todoService.createTodo(TodoConverter.toDomain(TodoConverter.stringToDTO(json))))
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
        return todoService.deleteTodo(id)

    }
}
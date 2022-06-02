package br.com.gabriellucena98.todolist.service

import br.com.gabriellucena98.todolist.model.Todo
import br.com.gabriellucena98.todolist.repository.TodoRepository
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {

    fun getAll(): List<Todo>{
        return todoRepository.getAll()
    }

    fun createTodo(todo: Todo): Todo{
        return todoRepository.createTodo(todo)
    }

    fun editTodo(todo: Todo): Todo {
        return todoRepository.editTodo(todo)
    }

    fun deleteTodo(id: UUID){
        return todoRepository.deleteTodo(id)
    }

}
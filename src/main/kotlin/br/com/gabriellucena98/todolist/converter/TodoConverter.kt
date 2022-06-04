package br.com.gabriellucena98.todolist.converter

import br.com.gabriellucena98.todolist.dto.TodoDTO
import br.com.gabriellucena98.todolist.model.Todo
import java.util.UUID

object TodoConverter {


    fun toDTO(todo: Todo): TodoDTO {
        return with(todo) {
            TodoDTO(
                id = id,
                name = name,
                done = done
            )
        }

    }

    fun toDomain(todoDTO: TodoDTO): Todo{
        return with(todoDTO) {
            Todo(
                id = id ?: UUID.randomUUID(),
                name = name,
                done = done ?: false
            )
        }
    }

    fun stringToDTO(json: String): TodoDTO {
        return with(json) {
            TodoDTO(
                id = null,
                name = json,
                done = false
            )
        }
    }


}
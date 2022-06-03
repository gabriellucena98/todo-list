package br.com.gabriellucena98.todolist.repository

import br.com.gabriellucena98.todolist.model.Todo
import br.com.gabriellucena98.todolist.repository.mappers.TodoMapper
import java.util.UUID
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class TodoRepository(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) {
    private val SELECT_ALL_TODOS = """
        SELECT * FROM todo;
    """.trimIndent()
    private val DELETE_TODO = """
        DELETE FROM todo WHERE id = :id
    """.trimIndent()
    private val INSERT_TODO = """
        INSERT INTO todo (id, name, done)
        VALUES (gen_random_uuid(), :name, false)
        RETURNING *
    """.trimIndent()
    private val UPDATE_TODO = """
        UPDATE todo SET name = :name, done = :done WHERE id = :id
        RETURNING *
    """.trimIndent()

    fun getAll(): List<Todo>{
        return namedParameterJdbcTemplate.query(SELECT_ALL_TODOS, TodoMapper() )
    }

    fun createTodo(todo: Todo): Todo{
        val params = mapOf(
            "name" to todo.name
        )
        return namedParameterJdbcTemplate.query(INSERT_TODO, params, TodoMapper()).first()
    }

    fun editTodo(todo: Todo): Todo{
        val params = mapOf(
            "id" to todo.id,
            "name" to todo.name,
            "done" to todo.done
        )
        return namedParameterJdbcTemplate.query(UPDATE_TODO, params, TodoMapper()).first()
    }

    fun deleteTodo(id: UUID){
        namedParameterJdbcTemplate.update(DELETE_TODO, mapOf("id" to id))
    }


}
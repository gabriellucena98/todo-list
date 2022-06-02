package br.com.gabriellucena98.todolist.repository.mappers

import br.com.gabriellucena98.todolist.model.Todo
import java.sql.ResultSet
import java.util.UUID
import org.springframework.jdbc.core.RowMapper

class TodoMapper: RowMapper<Todo> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Todo {
        return Todo(
            id = UUID.fromString(rs.getString("id")),
            name = rs.getString("name"),
            done = rs.getBoolean("done")
        )
    }
}
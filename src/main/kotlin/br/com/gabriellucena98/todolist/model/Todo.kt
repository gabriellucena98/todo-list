package br.com.gabriellucena98.todolist.model

import java.util.UUID

data class Todo(
    val id: UUID,
    val name: String,
    val done: Boolean
)

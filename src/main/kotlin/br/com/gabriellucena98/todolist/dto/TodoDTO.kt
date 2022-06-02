package br.com.gabriellucena98.todolist.dto

import java.util.UUID

data class TodoDTO (
    val id: UUID?,
    val name: String,
    val done: Boolean?
)
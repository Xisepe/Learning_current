package ru.golubev.learning_current.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val password: String
)

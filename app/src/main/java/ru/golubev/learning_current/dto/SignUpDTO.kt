package ru.golubev.learning_current.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignUpDTO(
    val username: String,
    val password: String
)
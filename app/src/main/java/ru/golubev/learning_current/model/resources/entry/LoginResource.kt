package ru.golubev.learning_current.model.resources.entry

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import ru.golubev.learning_current.model.User

@Serializable
@Resource("login")
class LoginResource(val user: User) {
}
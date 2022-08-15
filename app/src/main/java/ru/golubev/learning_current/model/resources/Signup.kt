package ru.golubev.learning_current.model.resources

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import ru.golubev.learning_current.model.User

@Serializable
@Resource("signup")
class Signup(val user: User) {
}
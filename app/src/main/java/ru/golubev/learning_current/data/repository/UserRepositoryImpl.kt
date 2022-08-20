package ru.golubev.learning_current.data.repository

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.resources.*
import ru.golubev.learning_current.data.UserRepository
import ru.golubev.learning_current.model.User
import ru.golubev.learning_current.model.resources.entry.SignupResource

class UserRepositoryImpl(private val client: HttpClient): UserRepository {
    override suspend fun saveUser(user: User): String {
        return client.post(SignupResource(user)).body()
    }



}
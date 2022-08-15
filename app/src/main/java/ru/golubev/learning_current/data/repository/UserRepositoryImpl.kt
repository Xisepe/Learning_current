package ru.golubev.learning_current.data.repository

import io.ktor.client.*
import io.ktor.client.plugins.resources.*
import ru.golubev.learning_current.data.UserRepository
import ru.golubev.learning_current.model.User
import ru.golubev.learning_current.model.resources.Signup
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val client: HttpClient): UserRepository {
    override suspend fun saveUser(user: User) {
        val httpResponse = client.post(Signup(user))
    }

    override suspend fun getUser(): User {
        TODO("Not yet implemented")
    }

}
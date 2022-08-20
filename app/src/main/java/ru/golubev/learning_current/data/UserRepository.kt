package ru.golubev.learning_current.data

import ru.golubev.learning_current.model.User

interface UserRepository{
    suspend fun saveUser(user: User): String

}
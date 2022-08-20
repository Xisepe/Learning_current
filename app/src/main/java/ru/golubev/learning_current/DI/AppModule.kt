package ru.golubev.learning_current.DI

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import ru.golubev.learning_current.data.UserRepository
import ru.golubev.learning_current.data.repository.UserRepositoryImpl
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUserRepository(client: HttpClient): UserRepository {
        return UserRepositoryImpl(client)
    }
}


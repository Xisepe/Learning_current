package ru.golubev.learning_current.data.remote

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HttpClientModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        followRedirects = false
        expectSuccess = true
        //Request
        install(DefaultRequest) {
            url(HttpClientConfig.BASE_URL)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        //Type safe
        install(Resources)
        //Logs
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("Ktor Logger", message)
                }
            }
            level = LogLevel.ALL
        }
        //Retries
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = HttpClientConfig.MAX_HTTP_REQUEST_RETRIES)
            exponentialDelay()
        }
        //Content(JSON)
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        //log responses
        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }

        engine {
            connectTimeout = HttpClientConfig.TIME_OUT
            socketTimeout = HttpClientConfig.TIME_OUT
        }
    }

}
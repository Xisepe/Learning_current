package ru.golubev.learning_current.data.remote

object HttpClientConfig {
    const val BASE_URL = "https://mock-banking-application.herokuapp.com/"
    const val TIME_OUT = 60_000
    const val MAX_HTTP_REQUEST_RETRIES = 3
}
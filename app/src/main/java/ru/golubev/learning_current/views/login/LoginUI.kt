package ru.golubev.learning_current.views.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

typealias OnChanged = (String) -> Unit

@Composable
fun LoginUI() {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

}

@Composable
fun LoginUsername(
    username: String,
    onUsernameChanged: OnChanged
) {

}

@Composable
fun LoginPassword(
    password: String,
    onPasswordChanged: OnChanged
) {

}
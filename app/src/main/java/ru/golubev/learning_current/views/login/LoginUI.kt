package ru.golubev.learning_current.views.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.golubev.learning_current.R
import ru.golubev.learning_current.ui.theme.AppTheme


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginUI(
    navController: NavController
) {

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val signUpScope = rememberCoroutineScope()

    AppTheme(darkTheme = true) {
        Scaffold(backgroundColor = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        focusManager.clearFocus(true)
                        keyboardController?.hide()
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .weight(1f),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                )
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .weight(2f)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        OutlinedTextField(
                            value = username,
                            onValueChange = {
                                username = it
                            },
                            label = { Text(text = "Username") },
                            trailingIcon = {
                                if (username.isNotEmpty()) {
                                    IconButton(onClick = { username = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = "Clear button icon"
                                        )
                                    }
                                }
                            },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            ),
                            singleLine = true,
                            shape = MaterialTheme.shapes.medium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            label = { Text(text = "Password") },
                            trailingIcon = {
                                if (password.isNotEmpty()) {
                                    IconButton(onClick = {
                                        isPasswordVisible = !isPasswordVisible
                                    }) {
                                        Icon(
                                            imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                            contentDescription = "Visibility button icon"
                                        )
                                    }
                                }
                            },
                            visualTransformation = if (!isPasswordVisible) {
                                PasswordVisualTransformation()
                            } else VisualTransformation.None,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {

                                }
                            ),
                            singleLine = true,
                            shape = MaterialTheme.shapes.medium,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
//                                val response = signUpScope.launch {
//                                    client.post("https://mock-banking-application.herokuapp.com/signup") {
//                                        contentType(ContentType.Application.Json)
//                                        setBody(SignUpDTO(username, password))
//                                    }
//                                }

                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text("Login")
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Top
                        ) {
                            TextButton(onClick = { navController.navigate("signup") }) {
                                Text(text = "Don't have an account?")
                            }
                        }
                    }
                }
            }
        }
    }

}
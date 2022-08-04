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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.golubev.learning_current.R
import ru.golubev.learning_current.ui.theme.AppTheme

@ExperimentalComposeUiApi
@Preview
@Composable
fun LoginUI() {

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val passwordValidator

    val isPasswordValid by remember {
        derivedStateOf {

        }
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

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
                        modifier = Modifier.fillMaxSize(),
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
                            singleLine = true,
                            shape = MaterialTheme.shapes.medium
                        )
                    }
                }
            }
        }
    }

}

class ValidationOptions(
    val minLength: Int = 8,
    val containsDigit: Boolean = false,
    val containsSpecialSymbol: Boolean = false,
    val containsUppercase: Boolean = false,
    val containsLowercase: Boolean = false
)

interface Validator {
    fun validate(text: String): ValidationResult
    fun isValid(result: ValidationResult): Boolean
    fun message(result: ValidationResult): String
}

enum class ValidationResult{
    OK, INVALID_LENGTH, WHITESPACE, NO_DIGIT, NO_SPECIAL_SYMBOL, NO_UPPERCASE, NO_LOWERCASE,
}

class PasswordValidator(
    private val options: ValidationOptions = ValidationOptions()
): Validator {
    override fun validate(password: String): ValidationResult {
        return when {
            password.length < options.minLength -> ValidationResult.INVALID_LENGTH
            password.contains("\\s".toRegex()) -> ValidationResult.WHITESPACE
            options.containsDigit && !password.contains("[0-9]".toRegex()) -> ValidationResult.NO_DIGIT
            options.containsSpecialSymbol && !password.contains("[@#\$%^&+=_]".toRegex()) -> ValidationResult.NO_SPECIAL_SYMBOL
            options.containsLowercase && !password.contains("[a-z]".toRegex()) -> ValidationResult.NO_LOWERCASE
            options.containsUppercase && !password.contains("[A-Z]".toRegex()) -> ValidationResult.NO_UPPERCASE
            else -> ValidationResult.OK
        }
    }
    override fun isValid(result: ValidationResult) = result == ValidationResult.OK
    override fun message(result: ValidationResult) = when (result) {
        ValidationResult.INVALID_LENGTH -> "password must be at least ${options.minLength} symbols"
        ValidationResult.WHITESPACE -> "no whitespace allowed in the password"
        ValidationResult.NO_DIGIT -> "a digit must occur at least once"
        ValidationResult.NO_SPECIAL_SYMBOL -> "a special character must occur at least once"
        ValidationResult.NO_UPPERCASE -> "a lower case letter must occur at least once"
        ValidationResult.NO_LOWERCASE -> "an upper case letter must occur at least once"
        ValidationResult.OK -> "OK"
    }

}


package ru.golubev.learning_current

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.golubev.learning_current.views.login.LoginUI
import ru.golubev.learning_current.views.login.SignupUI

@OptIn(ExperimentalComposeUiApi::class)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "login") {
                composable("login"){LoginUI(navController)}
                composable("signup"){SignupUI(navController)}
            }
        }
    }
}



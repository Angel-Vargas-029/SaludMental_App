// app/src/main/java/com/example/saludmental/navigation/NavGraph.kt
package com.example.saludmental.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saludmental.ui.screens.*
import com.example.saludmental.ui.viewmodel.AuthViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = viewModel()
) {
    val isUserSignedIn by authViewModel.isUserSignedIn.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (isUserSignedIn) "home" else "login"
    ) {
        // Pantallas de autenticación
        composable("login") {
            LoginScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("register") {
            RegisterScreen(navController = navController, authViewModel = authViewModel)
        }

        // Pantallas principales (requieren autenticación)
        composable("home") {
            HomeScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("diary") {
            DiaryScreen(navController = navController)
        }
        composable("music") {
            MusicScreen(navController = navController)
        }
        composable("appointments") {
            AppointmentsScreen(navController = navController)
        }
        composable("history") {
            HistoryScreen(navController = navController)
        }
    }
}
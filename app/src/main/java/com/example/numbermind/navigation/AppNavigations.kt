package com.example.numbermind.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.numbermind.models.ViewModelJuego
import com.example.numbermind.screens.GameScreen
import com.example.numbermind.screens.InstruccionesScreen

@Composable
fun appNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.InstruccionesScreen.route) {
        composable(route = AppScreens.InstruccionesScreen.route) { InstruccionesScreen(navController) }
        composable(route = AppScreens.GameScreen.route) { GameScreen(navController, mvvm = ViewModelJuego()) }
    }
}

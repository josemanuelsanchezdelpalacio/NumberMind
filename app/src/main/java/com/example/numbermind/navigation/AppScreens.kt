package com.example.numbermind.navigation
sealed class AppScreens (val route: String){
    object InstruccionesScreen: AppScreens(route = "instrucciones_screen")
    object GameScreen: AppScreens(route = "game_screen")
}

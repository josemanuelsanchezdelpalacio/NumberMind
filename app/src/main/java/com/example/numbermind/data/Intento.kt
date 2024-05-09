package com.example.numbermind.data

data class Intento(
    var numeroIntento: Int = 0,
    var numeroJugador: String = "",
    var bienColocados: Int = 0,
    var malColocados: Int = 0,
    var noExisten: Int = 0
)
package com.example.numbermind.states

import com.example.numbermind.data.Intento

data class UiState(
    var numeroJugador: String = "",
    val numeroSecreto: String = "",
    var intentosAnteriores: List<Intento> = emptyList(),
    var resultado: String = ""
)
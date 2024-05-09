package com.example.numbermind.models

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.lifecycle.ViewModel
import com.example.numbermind.data.Intento
import com.example.numbermind.states.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ViewModelJuego : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    //establezco el numero secreto generado de forma aleatoria
    init {
        _uiState.value = _uiState.value.copy(numeroSecreto = generarNumeroAleatorio().toString())
    }


    //para actualizar el numero del jugador
    fun changedNumero(numeroJugador: String) {
        _uiState.value = _uiState.value.copy(numeroJugador = numeroJugador)
    }

    //funcion para comprobar cada uno de los numeros
    fun comprobarNumero(numero: String, context: Context) {
        val numeroAleatorio = _uiState.value.numeroSecreto

        if (numero.length != 4 || numero.isBlank()) {
            Toast.makeText(context, "El numero no puede estar vacio o tener menos de 4 cifras", Toast.LENGTH_SHORT).show()
            return
        }

        //creo un nuevo intento
        val nuevoIntento = Intento(
            numeroIntento = _uiState.value.intentosAnteriores.size + 1,
            numeroJugador = numero,
            bienColocados = 0,
            malColocados = 0,
            noExisten = 0
        )

        //recorro el numero del usuario
        for (i in numero.indices) {
            //compruebo que los numeros esten bien colocados, mal colocados o que no existan
            if (numero[i] == numeroAleatorio[i]) {
                nuevoIntento.bienColocados++
            } else if (numeroAleatorio.contains(numero[i])) {
                nuevoIntento.malColocados++
            } else {
                nuevoIntento.noExisten++
            }
        }

        //agrego el nuevo intento a la lista de intentos anteriores
        val nuevosIntentos = _uiState.value.intentosAnteriores.toMutableList()
        nuevosIntentos.add(nuevoIntento)

        //actualizo con los nuevos intentos
        _uiState.value = _uiState.value.copy(intentosAnteriores = nuevosIntentos, numeroJugador = "")

        // Compruebo si el jugador ha ganado o perdido
        if (nuevoIntento.bienColocados == 4) {
            _uiState.value = _uiState.value.copy(
                resultado = "Has ganado. Número de intentos: ${nuevosIntentos.size}",
            )
        } else if (nuevosIntentos.size >= 10) {
            _uiState.value = _uiState.value.copy(
                resultado = "Has perdido. Vuelve a intentarlo.",
            )
        }
    }

    fun generarMatrizCirculos(intento: Intento): IntArray {
        return IntArray(4) { index ->
            when {
                //si el numero de bien colocados es mayor que el indice actual doy el valor 0
                intento.numeroJugador[index] == _uiState.value.numeroSecreto[index] -> 0 //bien colocados

                //si el numero  mal colocados es mayor que el indice actual doy el valor 1
                _uiState.value.numeroSecreto.contains(intento.numeroJugador[index]) -> 1 //mal colocados

                //si no se le da el valor 2
                else -> 2
            }
        }
    }



    fun generarNumeroAleatorio(): Int {
        return (1000..9999).random()
    }

    fun reiniciarJuego() {
        _uiState.value = UiState(numeroSecreto = generarNumeroAleatorio().toString())
    }
}


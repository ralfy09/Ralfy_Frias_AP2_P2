package com.example.ralfy_frias_ap2_p2.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import com.example.ralfy_frias_ap2_p2.data.remote.remotedatasource.JugadorRemoteDataSource
import com.example.ralfy_frias_ap2_p2.domain.model.JugadorRequest
import com.example.ralfy_frias_ap2_p2.domain.model.JugadorResponse

class JugadorViewModel(
    private val remoteDataSource: JugadorRemoteDataSource
) : ViewModel() {

    // Lista de jugadores para la pantalla
    var jugadores = mutableStateListOf<JugadorResponse>()
        private set

    // Jugador seleccionado (para editar)
    var jugadorSeleccionado by mutableStateOf<JugadorResponse?>(null)
        private set

    // Cargar lista de jugadores
    fun obtenerJugadores() {
        viewModelScope.launch {

            val result = remoteDataSource.getJugadores()

            result.onSuccess { lista ->
                jugadores.clear()
                jugadores.addAll(lista)
            }

            result.onFailure {
                println("Error al obtener jugadores")
            }
        }
    }

    // Obtener jugador por ID
    fun obtenerJugadorPorId(id: Int) {
        viewModelScope.launch {

            val result = remoteDataSource.getJugadorById(id)

            result.onSuccess {
                jugadorSeleccionado = it
            }

            result.onFailure {
                println("Error al obtener jugador")
            }
        }
    }

    // Crear jugador
    fun crearJugador(nombres: String, email: String) {
        viewModelScope.launch {

            val request = JugadorRequest(
                nombres = nombres,
                email = email
            )

            val result = remoteDataSource.crearJugador(request)

            result.onSuccess {
                obtenerJugadores()
            }

            result.onFailure {
                println("Error al crear jugador")
            }
        }
    }

    fun actualizarJugador(
        id: Int,
        nombres: String,
        email: String
    ) {
        viewModelScope.launch {

            val request = JugadorRequest(
                nombres = nombres,
                email = email
            )

            val result = remoteDataSource.actualizarJugador(id, request)

            result.onSuccess {
                obtenerJugadores()
            }

            result.onFailure {
                println("Error al actualizar jugador")
            }
        }
    }
}
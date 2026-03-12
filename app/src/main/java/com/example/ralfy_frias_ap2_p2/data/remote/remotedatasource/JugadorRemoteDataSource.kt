package com.example.ralfy_frias_ap2_p2.data.remote.remotedatasource

import com.example.ralfy_frias_ap2_p2.data.remote.Api.JugadorApi
import com.example.ralfy_frias_ap2_p2.domain.model.JugadorRequest
import com.example.ralfy_frias_ap2_p2.domain.model.JugadorResponse

class JugadorRemoteDataSource(
    private val api: JugadorApi
) {

    suspend fun getJugadores(): Result<List<JugadorResponse>> {
        return try {

            val response = api.getJugadores()

            if (!response.isSuccessful) {
                Result.failure(Exception("Error ${response.code()}"))
            } else {
                Result.success(response.body()!!)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getJugadorById(id: Int): Result<JugadorResponse> {
        return try {

            val response = api.getJugadorById(id)

            if (!response.isSuccessful) {
                Result.failure(Exception("Error ${response.code()}"))
            } else {
                Result.success(response.body()!!)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun crearJugador(jugador: JugadorRequest): Result<Unit> {
        return try {

            val response = api.crearJugador(jugador)

            if (!response.isSuccessful) {
                Result.failure(Exception("Error ${response.code()}"))
            } else {
                Result.success(Unit)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun actualizarJugador(
        id: Int,
        jugador: JugadorRequest
    ): Result<Unit> {

        return try {

            val response = api.actualizarJugador(id, jugador)

            if (!response.isSuccessful) {
                Result.failure(Exception("Error ${response.code()}"))
            } else {
                Result.success(Unit)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
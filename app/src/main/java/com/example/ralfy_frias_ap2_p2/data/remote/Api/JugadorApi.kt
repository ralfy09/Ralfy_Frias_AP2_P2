package com.example.ralfy_frias_ap2_p2.data.remote.Api

import com.example.ralfy_frias_ap2_p2.domain.model.JugadorRequest
import com.example.ralfy_frias_ap2_p2.domain.model.JugadorResponse
import retrofit2.Response
import retrofit2.http.*

interface JugadorApi {

    // GET /api/Jugadores
    @GET("api/Jugadores")
    suspend fun getJugadores(): Response<List<JugadorResponse>>

    // GET /api/Jugadores/{id}
    @GET("api/Jugadores/{id}")
    suspend fun getJugadorById(
        @Path("id") id: Int
    ): Response<JugadorResponse>

    // POST /api/Jugadores
    @POST("api/Jugadores")
    suspend fun crearJugador(
        @Body jugador: JugadorRequest
    ): Response<Unit>

    // PUT /api/Jugadores/{id}
    @PUT("api/Jugadores/{id}")
    suspend fun actualizarJugador(
        @Path("id") id: Int,
        @Body jugador: JugadorRequest
    ): Response<Unit>
}
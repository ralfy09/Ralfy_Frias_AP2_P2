package com.example.ralfy_frias_ap2_p2.presentacion

import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ralfy_frias_ap2_p2.ViewModel.JugadorViewModel

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: JugadorViewModel
) {

    val jugadores = viewModel.jugadores

    LaunchedEffect(Unit) {
        viewModel.obtenerJugadores()
    }

    Scaffold(

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("crear")
                }
            ) {
                Text("+")
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {

                items(jugadores) { jugador ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                navController.navigate("editar/${jugador.jugadorId}")
                            }
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text("ID: ${jugador.jugadorId}")
                            Text("Nombre: ${jugador.nombres}")
                            Text("Email: ${jugador.email}")

                        }
                    }

                }

                item {
                    Text(
                        text = "Total de jugadores: ${jugadores.size}",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

            }

        }

    }
}
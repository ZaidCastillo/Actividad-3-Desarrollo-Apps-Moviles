package com.example.eventregister.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventregister.ui.screens.EventFormScreen
import com.example.eventregister.ui.screens.EventListScreen
import com.example.eventregister.viewmodel.EventViewModel

/**
 * Grafo de navegación de la app.
 *
 * El EventViewModel se obtiene una sola vez aquí, en el nivel del NavHost
 * (con hiltViewModel, inyectado por Hilt), y se pasa como parámetro a ambas
 * pantallas. Así ambas comparten la misma instancia y la misma lista de
 * eventos, sin necesidad de un ViewModel "global" ni de pasar datos por argumentos.
 */
@Composable
fun EventNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val eventViewModel: EventViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.EventList.route
    ) {
        composable(Screen.EventList.route) {
            EventListScreen(
                viewModel = eventViewModel,
                onAddEvent = { navController.navigate(Screen.EventForm.route) }
            )
        }
        composable(Screen.EventForm.route) {
            EventFormScreen(
                viewModel = eventViewModel,
                onEventSaved = { navController.popBackStack() },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}

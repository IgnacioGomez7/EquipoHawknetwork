package com.example.equipohawknetwork.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.equipohawknetwork.model.EventViewModel
import com.example.equipohawknetwork.ui.EventFormScreen
import com.example.equipohawknetwork.ui.EventListScreen

object EventRoutes { const val LIST = "list"; const val FORM = "form" }

@OptIn(ExperimentalMaterial3Api::class) // para DatePicker usado en el formulario
@Composable
fun EventsNav(
    navController: NavHostController = rememberNavController(),
    vm: EventViewModel = hiltViewModel()
) {
    NavHost(navController = navController, startDestination = EventRoutes.LIST) {
        composable(EventRoutes.LIST) {
            EventListScreen(onAddClick = { navController.navigate(EventRoutes.FORM) }, vm = vm)
        }
        composable(EventRoutes.FORM) {
            EventFormScreen(onSaved = { navController.popBackStack() }, vm = vm)
        }
    }
}


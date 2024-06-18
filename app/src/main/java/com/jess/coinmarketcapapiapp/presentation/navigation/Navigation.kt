package com.jess.coinmarketcapapiapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jess.coinmarketcapapiapp.presentation.info.InfoScreen
import com.jess.coinmarketcapapiapp.presentation.list.ListScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.List.name) {
        composable(route = List.route) { ListScreen(navController = navController) }
        composable(
            route = Info.routeWithArgs,
            arguments = Info.arguments,
        ) { entry ->
            val symbol = entry.arguments?.getString(Info.symbolArg)
            InfoScreen(navController = navController, symbol = symbol)
        }
    }
}
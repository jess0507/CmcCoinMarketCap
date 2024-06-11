package com.jess.coinmarketcapapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jess.coinmarketcapapi.presentation.info.InfoScreen
import com.jess.coinmarketcapapi.presentation.list.ListScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.List.name) {
        composable(Screen.List.name) { ListScreen(navController = navController) }
        composable(Screen.Info.name) { InfoScreen(navController = navController) }
    }
}
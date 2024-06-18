package com.jess.coinmarketcapapiapp.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlin.collections.List

interface Destination {
    val icon: String?
    val route: String
    val routeWithArgs: String
    val arguments: List<NamedNavArgument>
}

object List : Destination {
    override lateinit var icon: String
    override val route: String = "List"
    override val routeWithArgs: String = route
    override val arguments: List<NamedNavArgument> = listOf()
}

object Info : Destination {
    override var icon: String? = null
    override val route: String = "Info"
    const val symbolArg = "symbol"
    override val routeWithArgs = "$route/{$symbolArg}"
    override val arguments: List<NamedNavArgument> = listOf(
        navArgument(symbolArg) { type = NavType.StringType }
    )
}
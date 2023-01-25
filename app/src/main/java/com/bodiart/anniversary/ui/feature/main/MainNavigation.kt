package com.bodiart.anniversary.ui.feature.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bodiart.anniversary.ui.feature.home.HomeScreen


/**
 * Fast navigation implementation
 */

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationNode.Home.route) {
        composable(NavigationNode.Home.route) {
            HomeScreen()
        }
    }
}

sealed class NavigationNode(val route: String) {
    object Home : NavigationNode("home")
}
package com.example.stocks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stocks.presentation.company_listings.CompanyListingsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.CompanyListingsScreen.name
    ) {
        composable(AppScreens.CompanyListingsScreen.name) {
            CompanyListingsScreen(controller = navController)
        }
    }
}
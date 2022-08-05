package com.example.stocks.navigation

enum class AppScreens {
    CompanyListingsScreen;

    companion object {
        fun fromRoute(route: String?): AppScreens {
            return when (route?.substringBefore("/")) {
                CompanyListingsScreen.name -> CompanyListingsScreen
                null -> CompanyListingsScreen
                else -> throw IllegalStateException("Route $route is not recognized!")
            }

        }
    }
}
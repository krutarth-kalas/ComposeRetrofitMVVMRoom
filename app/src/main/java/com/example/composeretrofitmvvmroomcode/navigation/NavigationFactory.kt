package com.example.composeretrofitmvvmroomcode.navigation

import com.example.composeretrofitmvvmroomcode.model.Product

sealed class NavigationFactory(val route : String)
{
    object DashboardScreen : NavigationFactory("dashboard")
    object DetailsScreen : NavigationFactory("detailsScreen")
    /*{
        fun createRoute(data: Product) = "$data/detailsScreen"
    }*/
}

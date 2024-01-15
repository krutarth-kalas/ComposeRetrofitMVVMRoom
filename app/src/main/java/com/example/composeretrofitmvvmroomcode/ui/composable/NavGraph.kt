package com.example.composeretrofitmvvmroomcode.ui.composable

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeretrofitmvvmroomcode.model.Product
import com.example.composeretrofitmvvmroomcode.navigation.NavigationFactory
import com.example.composeretrofitmvvmroomcode.viewmodel.CommonViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContent(context: Context, navHostController: NavHostController, viewModel: CommonViewModel)
{
    NavHost(navController = navHostController, startDestination = NavigationFactory.DashboardScreen.route)
    {
        composable(NavigationFactory.DashboardScreen.route){
            DashboardScreen(context,navHostController,viewModel)
        }
        composable(NavigationFactory.DetailsScreen.route){
            val product: Product? = navHostController.previousBackStackEntry?.savedStateHandle?.get<Product>("user") // new
            //val product = navHostController.previousBackStackEntry?.arguments?.getParcelable<Product>("USER")
            DetailsScreen(context,navHostController, product)
        }
    }
}

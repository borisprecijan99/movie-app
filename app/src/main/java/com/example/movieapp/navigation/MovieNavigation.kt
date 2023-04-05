package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapp.screens.details.DetailsScreen
import com.example.movieapp.screens.home.HomeScreen

@Composable
fun MovieNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {
        composable(route = MovieScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(route = MovieScreens.DetailsScreen.name + "/{id}", arguments = listOf(
            navArgument(name = "id"){
                type = NavType.StringType
            }
        )) {
            DetailsScreen(navController = navController, it.arguments?.getString("id"))
        }
    }
}
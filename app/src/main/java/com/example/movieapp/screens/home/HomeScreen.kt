package com.example.movieapp.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController) {
    MainContent(navController = navController)
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp)
    ) {
        items(movieList) {
            MovieRow(movie = it) {
                navController.navigate(route = MovieScreens.DetailsScreen.name + "/${it}")
            }
        }
    }
}
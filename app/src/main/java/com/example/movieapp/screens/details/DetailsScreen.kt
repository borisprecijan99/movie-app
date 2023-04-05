package com.example.movieapp.screens.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.MovieDetails

@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    val movie = getMovies().first { movie -> movie.id == movieId }
    MovieDetails(movie)
}
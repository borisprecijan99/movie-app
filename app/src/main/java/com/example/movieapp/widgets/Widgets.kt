package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies

@Preview
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16),
        elevation = 6.dp,
        onClick = { onItemClick(movie.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                elevation = 4.dp
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        //.transformations(CircleCropTransformation())
                        .build(),
                    contentDescription = "Movie Poster"
                )
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.caption)

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 13.sp
                                    )
                                ) {
                                    append("Plot: ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Light
                                    )
                                ) {
                                    append(movie.plot)
                                }

                            }, modifier = Modifier.padding(6.dp)
                        )

                        Divider(modifier = Modifier.padding(3.dp))
                        Text(
                            text = "Director: ${movie.director}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = "Actors: ${movie.actors}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = "Rating: ${movie.rating}",
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 25.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Arrow Up/Down",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                expanded = !expanded
                            },
                        tint = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MovieDetails(movie: Movie = getMovies()[0]) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(elevation = 4.dp) {
                AsyncImage(
                    model = movie.images[0],
                    contentDescription = "${movie.title} Image",
                    modifier = Modifier.size(100.dp)
                )
            }
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.caption)
            }
        }
        Text(
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.DarkGray,
                        fontSize = 13.sp
                    )
                ) {
                    append("Plot: ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.DarkGray,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light
                    )
                ) {
                    append(movie.plot)
                }
            }
        )
        Divider()
        Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.caption)

        MovieImages(movie)
    }
}

@Composable
fun MovieImages(movie: Movie) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Movie Images",
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center
    )
    LazyRow {
        items(movie.images) { imageUrl ->
            Surface(elevation = 4.dp, modifier = Modifier.size(300.dp)) {
                AsyncImage(
                    modifier = Modifier.size(300.dp),
                    model = imageUrl, contentDescription = "Movie Image"
                )
            }
        }
    }
}
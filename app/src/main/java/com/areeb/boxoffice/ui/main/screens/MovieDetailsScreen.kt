package com.areeb.boxoffice.ui.main.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.areeb.boxoffice.ui.common.components.DefaultContainer
import com.areeb.boxoffice.ui.common.components.ErrorState
import com.areeb.boxoffice.ui.main.screens.components.MovieDetailsMainSection
import com.areeb.boxoffice.ui.main.screens.components.MovieDetailsTabs
import com.areeb.boxoffice.ui.main.screens.components.MovieDetailsTopBar
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme
import org.koin.androidx.compose.koinViewModel


const val MOVIE_ID_KEY = "movie_id"
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreens(movieId: Int,mainNavController: NavHostController) {

    val viewModel = koinViewModel<MovieDetailsViewModel>()
    val movieDetails by remember { viewModel.movieDetails }
    val errorMessage by rememberSaveable { viewModel.errorState }

    LaunchedEffect(key1 = true) {
        viewModel.getMovieDetails(movieId)
    }

    Scaffold(
        topBar = { MovieDetailsTopBar(movieId, mainNavController) },
    ) {  innerPadding ->

        DefaultContainer(modifier = Modifier.padding(innerPadding),viewModel = viewModel) {

            movieDetails?.let {
                Column {
                    MovieDetailsMainSection(it)
                    Spacer(modifier = Modifier.height(24.dp))
                    MovieDetailsTabs(movieDetails = it)
                }

            }

            errorMessage?.let {
                    ErrorState {
                        viewModel.getMovieDetails(movieId)
                    }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreensPreview() {
    BoxOfficeTheme {
        MovieDetailsScreens(1,rememberNavController())
    }
}
package com.areeb.boxoffice.ui.main.tabs.home.components.top_rated_list

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import com.areeb.boxoffice.ui.common.components.DefaultContainer
import com.areeb.boxoffice.ui.common.components.ErrorState
import com.areeb.boxoffice.ui.main.tabs.home.HomeViewModel
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme

import org.koin.androidx.compose.koinViewModel

@Composable
fun TopMoviesSection(
    navigateToMovieDetailsScreen: (movieId: Int) -> Unit
) {

    val viewModel = koinViewModel<HomeViewModel>()
    val movies by rememberSaveable { viewModel.topRatedMovies }

    val errorMessage by rememberSaveable { viewModel.errorState }


    LaunchedEffect(key1 = true) {
        viewModel.fetchTopRatedMovies()
    }

    DefaultContainer {

        movies?.let {
            TopMoviesList(movies = it) { movie ->  navigateToMovieDetailsScreen.invoke(movie.id)}
        }

        errorMessage?.let {
            if (movies == null) {
                ErrorState {
                    viewModel.fetchTopRatedMovies()
                }
            }
        }
    }



}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_MASK
)
@Composable
fun TopMoviesSectionPreview() {
    BoxOfficeTheme {
        TopMoviesSection {}
    }
}

package com.areeb.boxoffice.ui.main.tabs.home.tabs

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import com.areeb.boxoffice.data.cache.entity.MovieType
import com.areeb.boxoffice.ui.common.components.DefaultContainer
import com.areeb.boxoffice.ui.common.components.ErrorState
import com.areeb.boxoffice.ui.main.tabs.home.HomeViewModel
import com.areeb.boxoffice.ui.main.tabs.home.tabs.components.MoviesList
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme
import org.koin.androidx.compose.koinViewModel



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesTabContent(type: MovieType, navigateToMovieDetailsScreen: (movieId: Int) -> Unit) {

    val viewModel = koinViewModel<HomeViewModel>()
    val errorMessage by rememberSaveable { viewModel.errorState }
    val movies by rememberSaveable { when(type) {
        MovieType.NowPlaying -> viewModel.nowPlayingMovies
        MovieType.Popular -> viewModel.popularMovies
        MovieType.Upcoming -> viewModel.upcomingMovies
        MovieType.TopRated -> viewModel.topRatedMovies
    } }


    DisposableEffect(LocalLifecycleOwner.current) {
        viewModel.fetchMoviesByType(type)
        onDispose {
        }
    }


    DefaultContainer(viewModel = viewModel) {

        movies?.let {
            MoviesList(movies = it, {movie -> navigateToMovieDetailsScreen.invoke(movie.id)}, {viewModel.fetchMoviesByType(type, false)} )
        }


        errorMessage?.let {
            if (movies == null) {
                ErrorState {
                    viewModel.fetchMoviesByType(type)
                }
            }
        }

    }



}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_MASK)
@Composable
fun MoviesTabContentPreview() {
    BoxOfficeTheme {
        MoviesTabContent(MovieType.Upcoming) {}
    }
}

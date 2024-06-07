package com.areeb.boxoffice.ui.main.screens.tabs

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import com.areeb.boxoffice.ui.common.components.DefaultContainer
import com.areeb.boxoffice.ui.common.components.ErrorState
import com.areeb.boxoffice.ui.main.screens.MovieDetailsViewModel
import com.areeb.boxoffice.ui.main.screens.tabs.components.MovieCastList
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun MovieCast(movieId: Int) {


    val viewModel = koinViewModel<MovieDetailsViewModel>()
    val movieCast by remember { viewModel.movieCast }
    val errorMessage by rememberSaveable { viewModel.errorState }

    LaunchedEffect(key1 = true) {
        viewModel.fetchMovieCast(movieId)
    }

        DefaultContainer(viewModel = viewModel) {

            movieCast?.let {
                MovieCastList(casts = it)
            }

            errorMessage?.let {
                ErrorState {
                    viewModel.getMovieDetails(movieId)
                }
            }

        }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_MASK)
@Composable
fun MovieCastPreview() {
    BoxOfficeTheme {
        MovieCast(1)
    }
}

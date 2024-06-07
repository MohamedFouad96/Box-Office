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
import com.areeb.boxoffice.ui.main.screens.tabs.components.MovieReviewsList
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun MovieReviews(movieId: Int) {

    val viewModel = koinViewModel<MovieDetailsViewModel>()
    val movieReviews by remember { viewModel.movieReviews }
    val errorMessage by rememberSaveable { viewModel.errorState }

    LaunchedEffect(key1 = true) {
        viewModel.fetchMovieReviews(movieId)
    }

    DefaultContainer(viewModel = viewModel) {

        movieReviews?.let {
            MovieReviewsList(it)
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
fun MovieReviewsPreview() {
    BoxOfficeTheme {
        MovieReviews(1)
    }
}

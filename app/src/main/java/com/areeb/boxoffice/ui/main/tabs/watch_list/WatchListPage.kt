package com.areeb.boxoffice.ui.main.tabs.watch_list


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.ui.common.components.DefaultContainer
import com.areeb.boxoffice.ui.common.components.EmptyState
import com.areeb.boxoffice.ui.common.components.ErrorState
import com.areeb.boxoffice.ui.main.tabs.home.tabs.components.MoviesList
import com.areeb.boxoffice.ui.main.tabs.watch_list.components.WatchListTopBar
import org.koin.androidx.compose.koinViewModel
import com.areeb.boxoffice.R
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme

@Composable
fun WatchListPage(navigateToMovieDetailsScreen: (movieId: Int) -> Unit) {
    val viewModel = koinViewModel<WatchListViewModel>()
    val movies by remember { viewModel.movies }
    val errorMessage by rememberSaveable { viewModel.errorState }

    LaunchedEffect(key1 = true) {
        viewModel.getWatchListMovies()
    }

    Scaffold(
        topBar = { WatchListTopBar() },
    ) { innerPadding ->

        DefaultContainer(modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 32.dp), viewModel = viewModel) {

            movies?.let {

                if (it.isNotEmpty()) {
                    MoviesList(
                        movies = it,
                        onClick = { movie -> navigateToMovieDetailsScreen.invoke(movie.id) }) {}
                } else {
                    EmptyState(
                        title = stringResource(id = R.string.there_is_no_movie_yet),
                        message = stringResource(id = R.string.find_your_movie_by_type),
                        image = painterResource(
                            id = R.drawable.ic_empty_watch_list
                        )
                    )
                }

            }

            errorMessage?.let {
                ErrorState {
                    viewModel.getWatchListMovies()
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun WatchListPagePreview() {
    BoxOfficeTheme {
        WatchListPage { }
    }
}
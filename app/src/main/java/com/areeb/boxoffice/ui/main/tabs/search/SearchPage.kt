package com.areeb.boxoffice.ui.main.tabs.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.R
import com.areeb.boxoffice.ui.common.components.DefaultContainer
import com.areeb.boxoffice.ui.common.components.EmptyState
import com.areeb.boxoffice.ui.common.components.ErrorState
import com.areeb.boxoffice.ui.main.tabs.home.tabs.components.MoviesList
import com.areeb.boxoffice.ui.main.tabs.search.components.SearchPageTopBar
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme
import org.koin.androidx.compose.koinViewModel


const val SEARCH_QUERY_KEY = "search_key"
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchPage(query: String = "" ,navigateToMovieDetailsScreen: (movieId: Int) -> Unit) {

    val viewModel = koinViewModel<SearchViewModel>()
    val movies by remember { viewModel.movies }
    val errorMessage by rememberSaveable { viewModel.errorState }
    var searchText by rememberSaveable { mutableStateOf(query) }


    LaunchedEffect(key1 = query.isNotEmpty()) {
        viewModel.searchForMovies(query)
    }

    Scaffold {

        Column(modifier = Modifier.fillMaxSize()) {
            SearchPageTopBar(searchText, onChangeSearchValue = {searchText = it}) {
                viewModel.searchForMovies(searchText)
            }
            Spacer(modifier = Modifier.height(21.dp))

            DefaultContainer(modifier = Modifier.padding(horizontal = 32.dp), viewModel = viewModel) {

                movies?.let {

                    if (it.isNotEmpty()) {
                        MoviesList(
                            movies = it,
                            onClick = { movie -> navigateToMovieDetailsScreen.invoke(movie.id) }) {}
                    } else {
                        EmptyState(
                            title = stringResource(id = R.string.we_are_sorry),
                            message = stringResource(id = R.string.find_your_movie_by_type),
                            image = painterResource(
                                id = R.drawable.ic_no_results
                            )
                        )
                    }

                }

                errorMessage?.let {
                    ErrorState {
                        viewModel.searchForMovies(searchText)
                    }
                }

            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun SearchPagePreview() {
    BoxOfficeTheme {
        SearchPage { }
    }
}
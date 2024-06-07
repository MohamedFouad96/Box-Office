package com.areeb.boxoffice.ui.main.tabs.home.tabs.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.data.model.Movie
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme


@Composable
fun MoviesList(
    movies: List<Movie>,
    onClick: (Movie) -> Unit,
    loadMore: () -> Unit
) {

    val listState = rememberLazyListState()


    LazyColumn(state = listState,modifier = Modifier.wrapContentHeight(),content = {
        itemsIndexed(movies) { index , movie ->

            MovieItem( movie = movie) {
                onClick.invoke(movie)
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (index == movies.size - 1) {
                loadMore()
            }
        }
    })


    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull() }
            .collect { visibleItem ->
                if (visibleItem != null && visibleItem.index == movies.size - 5) {
                    loadMore()
                }
            }
    }
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_MASK
)
@Composable
fun MoviesListPreview() {
    BoxOfficeTheme {
        MoviesList(
            listOf(
                Movie(1, "title", "overview", "poster", "backdrop", "2020-20-03", 1.2),
                Movie(2, "title", "overview", "poster", "backdrop", "2020-20-03", 1.2),
                Movie(3, "title", "overview", "poster", "backdrop", "2020-20-03", 1.2)
            ),
         {} , {})
    }
}

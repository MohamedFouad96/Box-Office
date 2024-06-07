package com.areeb.boxoffice.ui.main.tabs.home.components.top_rated_list

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.data.model.Movie
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme


@Composable
fun TopMoviesList(
    movies: List<Movie>,
    onClick: (Movie) -> Unit
) {

    LazyRow(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight(), contentPadding = PaddingValues(start = 24.dp),content = {
        itemsIndexed(movies) { index,movie ->

            TopMovieItem(number = index+1, movie = movie) {
                onClick.invoke(movie)
            }

            Spacer(modifier = Modifier.width(20.dp))
        }
    })


}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_MASK
)
@Composable
fun TopMoviesListPreview() {
    BoxOfficeTheme {
        TopMoviesList(
            listOf(
                Movie(1, "title", "overview", "poster", "backdrop", "2020-20-03", 1.2),
                Movie(2, "title", "overview", "poster", "backdrop", "2020-20-03", 1.2),
                Movie(3, "title", "overview", "poster", "backdrop", "2020-20-03", 1.2)
            )
        ) {}
    }
}

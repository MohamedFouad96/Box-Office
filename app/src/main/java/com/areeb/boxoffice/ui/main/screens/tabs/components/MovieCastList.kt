package com.areeb.boxoffice.ui.main.screens.tabs.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.data.model.MovieCast
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme


@Composable
fun MovieCastList(
    casts: List<MovieCast>,
) {


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(casts) { cast ->
            MovieCastItem(cast)
        }

    }


}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_MASK
)
@Composable
fun MovieCastListPreview() {
    BoxOfficeTheme {
        MovieCastList(
            listOf(
                MovieCast(
                    "name",
                    "profile",
                ),
                MovieCast(
                    "name",
                    "profile",
                ),
                MovieCast(
                    "name",
                    "profile",
                ),
                MovieCast(
                    "name",
                    "profile",
                ),
                MovieCast(
                    "name",
                    "profile",
                ),
            ))
    }
}

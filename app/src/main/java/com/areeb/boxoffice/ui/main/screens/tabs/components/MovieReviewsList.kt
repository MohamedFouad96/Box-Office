package com.areeb.boxoffice.ui.main.screens.tabs.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.data.model.MovieReview
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme


private val buffer = 1

@Composable
fun MovieReviewsList(
    reviews: List<MovieReview>,
) {

    LazyColumn(modifier = Modifier.wrapContentHeight(),content = {
        items(reviews, {it.username}) { review ->
            MovieReviewItem(review)

            Spacer(modifier = Modifier.height(30.dp))
        }
    })


}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_MASK
)
@Composable
fun MovieReviewsListPreview() {
    BoxOfficeTheme {
        MovieReviewsList(
            listOf(
                MovieReview(
                    "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government.",
                    "Iqbal Shafiq Rozaan",
                    "",
                    10
                ),
                MovieReview(
                    "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government.",
                    "Iqbal Shafiq Rozaan",
                    "",
                    10
                ),
                MovieReview(
                    "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government.",
                    "Iqbal Shafiq Rozaan",
                    "",
                    10
                )
            ))
    }
}

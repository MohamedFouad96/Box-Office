package com.areeb.boxoffice.ui.main.tabs.home.components.top_rated_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.data.model.Movie
import com.areeb.boxoffice.ui.common.components.image.DefaultAsyncImage
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme


@Composable
fun TopMovieItem(number: Int, movie: Movie, onClick: () -> Unit) {
    Box(modifier = Modifier.clickable(onClick = onClick),contentAlignment = Alignment.BottomStart) {
        DefaultAsyncImage(
            modifier = Modifier
                .padding(bottom = 30.dp, start = 10.dp)
                .height(210.dp)
                .width(145.dp)
                .clip(RoundedCornerShape(16)),
            imageUrl = movie.posterPath
        )

        DefaultNumericOutlinedText(modifier =  Modifier.height(110.dp),text = number.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun TopMovieItemPreview() {
    BoxOfficeTheme {
        TopMovieItem(1, Movie(1, "title", "overview", "poster", "backdrop", "2020-20-03", 1.2)) {}
    }
}
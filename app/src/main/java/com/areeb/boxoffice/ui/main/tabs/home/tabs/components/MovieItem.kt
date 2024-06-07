package com.areeb.boxoffice.ui.main.tabs.home.tabs.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.ui.common.components.image.DefaultAsyncImage
import com.areeb.boxoffice.R
import androidx.compose.foundation.layout.fillMaxWidth
import com.areeb.boxoffice.data.model.Movie
import com.areeb.boxoffice.ui.theme.AmericanOrange
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)) {
        DefaultAsyncImage(
            modifier = Modifier
                .height(120.dp)
                .width(95.dp)
                .clip(RoundedCornerShape(16)),
            imageUrl = movie.posterPath
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(text = movie.title, style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(14.dp))
            Row {
                Image(painter = painterResource(id = R.drawable.ic_star), contentDescription = "")
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = movie.voteAverage.toString(), style = MaterialTheme.typography.labelMedium.copy(color = AmericanOrange))

            }

            Spacer(modifier = Modifier.height(5.dp))

            Row {
                Image(painter = painterResource(id = R.drawable.ic_calendar), contentDescription = "")
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = movie.releaseDate, style = MaterialTheme.typography.labelMedium.copy(color = Color.White))

            }
        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun MovieItemPreview() {
    BoxOfficeTheme {
        MovieItem(Movie(1, "title", "overview", "poster", "backdrop", "2020-20-03", 1.2)) {}
    }
}
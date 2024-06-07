package com.areeb.boxoffice.ui.main.screens.tabs.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.ui.common.components.image.DefaultAsyncImage
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import com.areeb.boxoffice.data.model.MovieReview
import com.areeb.boxoffice.ui.theme.BlueCola
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme


@Composable
fun MovieReviewItem(review: MovieReview) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DefaultAsyncImage(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape),
                imageUrl = review.avatarPath
                    ?: "https://media.istockphoto.com/id/1337144146/vector/default-avatar-profile-icon-vector.jpg?s=612x612&w=0&k=20&c=BIbFwuv7FxTWvh5S3vB6bkT0Qv8Vn8N5Ffseq84ClGI="
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(text = review
                .rating.toString(), style = MaterialTheme.typography.headlineLarge.copy(color = BlueCola))
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(text = review.username, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = review.content, style = MaterialTheme.typography.headlineMedium)

        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun MovieReviewItemPreview() {
    BoxOfficeTheme {
        MovieReviewItem(
            MovieReview(
                "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government.",
                "Iqbal Shafiq Rozaan",
                "",
                10
            )
        )
    }
}
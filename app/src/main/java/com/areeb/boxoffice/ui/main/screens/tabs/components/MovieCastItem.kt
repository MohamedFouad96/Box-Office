package com.areeb.boxoffice.ui.main.screens.tabs.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.ui.common.components.image.DefaultAsyncImage
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import com.areeb.boxoffice.data.model.MovieCast
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme

@Composable
fun MovieCastItem(cast: MovieCast) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DefaultAsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                imageUrl = cast.profilePath ?: "https://media.istockphoto.com/id/1337144146/vector/default-avatar-profile-icon-vector.jpg?s=612x612&w=0&k=20&c=BIbFwuv7FxTWvh5S3vB6bkT0Qv8Vn8N5Ffseq84ClGI="
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = cast.originalName, style = MaterialTheme.typography.headlineLarge)
        }


}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun MovieCastItemPreview() {
    BoxOfficeTheme {
        MovieCastItem(
            MovieCast(
                "name",
                "profile",
            )
        )
    }
}
package com.areeb.boxoffice.ui.main.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.R
import com.areeb.boxoffice.data.model.MovieDetails
import com.areeb.boxoffice.ui.common.components.image.DefaultAsyncImage
import com.areeb.boxoffice.ui.theme.AmericanOrange
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme
import com.areeb.boxoffice.ui.theme.CharlestonGreen
import com.areeb.boxoffice.ui.theme.SpanishGray


@Composable
fun MovieDetailsMainSection(movie: MovieDetails) {

    Column {

        Box(contentAlignment = Alignment.BottomStart) {

            Box(
                modifier = Modifier.padding(bottom = 60.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                DefaultAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                        .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)),
                    imageUrl = movie.backdropPath
                )

                MovieVote(movie.voteAverage)
            }


            MoviePoster(movie)

        }

        Spacer(modifier = Modifier.height(24.dp))

        MovieInfo(movie)

    }
}


@Composable
fun MovieVote(voteAvg: Double) {
    Row(
        Modifier
            .padding(horizontal = 11.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(CharlestonGreen.copy(alpha = 0.32f))
            .padding(start = 8.dp, end = 8.dp, top = 4.dp)) {
        Image(painter = painterResource(id = R.drawable.ic_star), contentDescription = "")
        Spacer(modifier = Modifier.width(3.dp))
        Text(text = voteAvg.toString(), style = MaterialTheme.typography.labelMedium.copy(color = AmericanOrange))

    }
}

@Composable
fun MoviePoster(movie: MovieDetails) {
    Row(modifier = Modifier.padding(horizontal = 30.dp),verticalAlignment = Alignment.Bottom) {
        DefaultAsyncImage(
            modifier = Modifier
                .height(120.dp)
                .width(95.dp)
                .clip(RoundedCornerShape(16)),
            imageUrl = movie.posterPath
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(text = movie.title, style = MaterialTheme.typography.titleLarge)

    }
}

@Composable
fun MovieInfo(movie: MovieDetails) {
    Row(
        Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

        Icon(
            painter = painterResource(id = R.drawable.ic_calendar),
            tint = SpanishGray,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = movie.releaseDate,
            style = MaterialTheme.typography.headlineLarge.copy(color = SpanishGray)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Spacer(modifier = Modifier
            .width(1.dp)
            .height(16.dp)
            .background(SpanishGray))
        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_clock),
            tint = SpanishGray,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = movie.runtime,
            style = MaterialTheme.typography.headlineLarge.copy(color = SpanishGray)
        )

        if (movie.genre != null) {

            Spacer(modifier = Modifier.width(12.dp))
            Spacer(modifier = Modifier
                .width(1.dp)
                .height(16.dp)
                .background(SpanishGray))
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_ticket),
                tint = SpanishGray,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = movie.genre,
                style = MaterialTheme.typography.headlineLarge.copy(color = SpanishGray)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MovieInfoPreview() {
    BoxOfficeTheme {
        MovieInfo(
            MovieDetails(
                1,
                "Spiderman No Way Home",
                "overview",
                "poster",
                "backdrop",
                "2020-20-03",
                "132 minutes",
                8.5,
                "action"
            )
        )
    }
}





@Preview(showBackground = true)
@Composable
fun MoviePosterPreview() {
    BoxOfficeTheme {
        MoviePoster(
            MovieDetails(
                1,
                "Spiderman No Way Home",
                "overview",
                "poster",
                "backdrop",
                "2020-20-03",
                "132 minutes",
                8.5,
                "action"
            )
        )
    }
}



@Preview(showBackground = true)
@Composable
fun MovieDetailsMainSectionPreview() {
    BoxOfficeTheme {
        MovieDetailsMainSection(
            MovieDetails(
                1,
                "Spiderman No Way Home",
                "overview",
                "poster",
                "backdrop",
                "2020-20-03",
                "132 minutes",
                8.5,
                "action"
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MovieVotePreview() {
    BoxOfficeTheme {
        MovieVote(
           9.5
        )
    }
}
package com.areeb.boxoffice.ui.main.screens.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.data.model.MovieDetails
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme
import com.areeb.boxoffice.ui.main.screens.tabs.MovieCast
import com.areeb.boxoffice.ui.main.screens.tabs.MovieOverview
import com.areeb.boxoffice.ui.main.screens.tabs.MovieReviews
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MovieDetailsTabs(modifier: Modifier = Modifier, movieDetails: MovieDetails) {

    val pagerState = rememberPagerState(initialPage = 0,pageCount = {4})
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier.padding(horizontal = 27.dp)) {

        MovieDetailsTabRow(pagerState.currentPage) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(it)
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(state = pagerState, userScrollEnabled = false) { page ->
            when(page) {
                0 -> MovieOverview(movieDetails.overview)
                1 -> MovieReviews(movieDetails.id)
                2 -> MovieCast(movieDetails.id)
            }
        }
    }




}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun HomeMovieByTypePreview() {
    BoxOfficeTheme {
        MovieDetailsTabs(movieDetails =    MovieDetails(
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
package com.areeb.boxoffice.ui.main.tabs.home.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.areeb.boxoffice.data.cache.entity.MovieType
import com.areeb.boxoffice.ui.main.tabs.home.components.MovieTabRow
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeMovieByType(modifier: Modifier = Modifier, navigateToMovieDetailsScreen: (movieId: Int) -> Unit) {

    val pagerState = rememberPagerState(initialPage = 0,pageCount = {4})
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier.padding(horizontal = 24.dp)) {

        MovieTabRow(pagerState.currentPage) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(it)
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(modifier = Modifier.fillMaxSize(),state = pagerState, userScrollEnabled = false) { page ->
            when(page) {
                0 -> MoviesTabContent(MovieType.NowPlaying , navigateToMovieDetailsScreen)
                1 -> MoviesTabContent(MovieType.Upcoming , navigateToMovieDetailsScreen)
                2 -> MoviesTabContent(MovieType.Popular , navigateToMovieDetailsScreen)
            }
        }
    }




}

@Preview(showBackground = true)
@Composable
fun HomeMovieByTypePreview() {
    BoxOfficeTheme {
        HomeMovieByType {}
    }
}
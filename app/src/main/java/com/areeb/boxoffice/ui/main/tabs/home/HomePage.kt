package com.areeb.boxoffice.ui.main.tabs.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.ui.main.tabs.home.components.HomePageTopBar
import com.areeb.boxoffice.ui.main.tabs.home.components.top_rated_list.TopMoviesSection
import com.areeb.boxoffice.ui.main.tabs.home.tabs.HomeMovieByType
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(navigateToSearchPage: (query: String) -> Unit, navigateToMovieDetailsScreen: (movieId: Int) -> Unit) {



    Scaffold {

        Column(modifier = Modifier.fillMaxSize()) {
            HomePageTopBar(navigateToSearchPage)
            Spacer(modifier = Modifier.height(21.dp))
            TopMoviesSection(navigateToMovieDetailsScreen)
            Spacer(modifier = Modifier.height(24.dp))
            HomeMovieByType(modifier = Modifier.weight(1f), navigateToMovieDetailsScreen)
        }

    }


}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    BoxOfficeTheme {
        HomePage({},{})
    }
}
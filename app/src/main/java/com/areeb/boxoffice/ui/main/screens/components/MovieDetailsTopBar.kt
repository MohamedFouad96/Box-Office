package com.areeb.boxoffice.ui.main.screens.components

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.areeb.boxoffice.R
import com.areeb.boxoffice.ui.main.screens.MovieDetailsViewModel
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsTopBar(movieId: Int,mainNavController: NavHostController) {

    val viewModel = koinViewModel<MovieDetailsViewModel>()
    val isBookmarked by remember { viewModel.isBookmarked }

    val layoutDirection = LocalLayoutDirection.current
    val isRtl = layoutDirection == LayoutDirection.Rtl



    LaunchedEffect(key1 = true) {
        viewModel.fetchMovieIsInWatchList(movieId)
    }


    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                stringResource(id = R.string.detail),
                style = MaterialTheme.typography.titleMedium
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                mainNavController.popBackStack()
            }) {
                Icon(
                    modifier = Modifier.graphicsLayer {
                        if (isRtl) {
                            scaleX = -1f  // Flip the icon horizontally for RTL layout
                        }
                    },
                    imageVector = Icons.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                viewModel.changeMovieBookmark(movieId, isBookmarked != true)
            }) {
                Image(painter = painterResource(id = if (isBookmarked == true) R.drawable.ic_bookmark_active else R.drawable.ic_bookmark), contentDescription = "")
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsTopBarPreview() {
    BoxOfficeTheme {
        MovieDetailsTopBar(1,rememberNavController())
    }
}
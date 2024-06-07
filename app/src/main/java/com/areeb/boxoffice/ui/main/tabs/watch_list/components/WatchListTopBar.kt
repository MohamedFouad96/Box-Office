package com.areeb.boxoffice.ui.main.tabs.watch_list.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.areeb.boxoffice.R
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchListTopBar() {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                stringResource(id = R.string.watch_list),
                style = MaterialTheme.typography.titleMedium
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun WatchListTopBarPreview() {
    BoxOfficeTheme {
        WatchListTopBar()
    }
}
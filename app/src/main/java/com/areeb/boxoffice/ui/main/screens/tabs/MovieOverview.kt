package com.areeb.boxoffice.ui.main.screens.tabs

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme


@Composable
fun MovieOverview(overview: String) {
    Text(text = overview, style = MaterialTheme.typography.headlineMedium)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_MASK)
@Composable
fun MovieDetailsTabRowPreview() {
    BoxOfficeTheme {
        MovieOverview("From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.")
    }
}

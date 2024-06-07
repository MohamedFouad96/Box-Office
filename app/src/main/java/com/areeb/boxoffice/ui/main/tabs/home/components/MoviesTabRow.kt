package com.areeb.boxoffice.ui.main.tabs.home.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.R
import com.areeb.boxoffice.ui.theme.Arsenic
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme


@Composable
fun MovieTabRow(tabIndex: Int,onChangeTab: (index: Int) -> Unit) {

    val tabs = listOf(stringResource(id = R.string.now_playing), stringResource(id = R.string.upcoming), stringResource(id = R.string.popular))


    TabRow(
        modifier = Modifier
            .height(42.dp)
        ,
        selectedTabIndex = tabIndex , containerColor = MaterialTheme.colorScheme.primaryContainer ,   divider = { },  indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                color = Arsenic,
                height = 5.dp,
            )
        },) {
        tabs.forEachIndexed { index, title ->
            Tab(
                modifier = Modifier
                    .height(42.dp)
                    .background(if (tabIndex == index) MaterialTheme.colorScheme.primary else Color.Transparent)
                ,text = {
                Text(title, maxLines = 1, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Medium))
                       },
                selected = tabIndex == index,
                onClick = {
                          onChangeTab.invoke(index)
                          },

            )
        }
    }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_MASK)
@Composable
fun MovieTabRowPreview() {
    BoxOfficeTheme {
        MovieTabRow(0) {}
    }
}

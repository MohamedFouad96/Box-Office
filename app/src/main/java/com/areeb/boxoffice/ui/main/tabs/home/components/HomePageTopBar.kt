package com.areeb.boxoffice.ui.main.tabs.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.R
import com.areeb.boxoffice.ui.common.components.textfields.SearchTextField
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme

@Composable
fun HomePageTopBar(navigateToSearchPage: (query: String) -> Unit) {
    var searchText by rememberSaveable { mutableStateOf("") }
    var searchTextIsError by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(horizontal = 24.dp)
        .fillMaxWidth()) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(stringResource(id = R.string.what_do_you_want_to_watch), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(24.dp))
        SearchTextField(searchText = searchText, onValueChange = {searchText = it}, isError = searchTextIsError) {
            searchTextIsError = searchText.isEmpty()
            if (searchText.isNotEmpty()) {
                navigateToSearchPage.invoke(searchText)
                searchText = ""
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePageTopBarPreview() {
    BoxOfficeTheme {
        HomePageTopBar({})
    }
}
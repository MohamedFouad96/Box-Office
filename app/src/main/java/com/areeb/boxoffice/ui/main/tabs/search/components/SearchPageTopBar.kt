package com.areeb.boxoffice.ui.main.tabs.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.R
import com.areeb.boxoffice.ui.common.components.textfields.SearchTextField
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme

@Composable
fun SearchPageTopBar(searchText: String, onChangeSearchValue: (text: String) ->  Unit ,onSubmitSearch: (query: String) -> Unit) {


    var searchTextIsError by rememberSaveable { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier
        .padding(horizontal = 24.dp)
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(stringResource(id = R.string.search), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(24.dp))
        SearchTextField(modifier = Modifier.focusRequester(focusRequester),searchText = searchText, onValueChange = onChangeSearchValue, isError = searchTextIsError) {
            searchTextIsError = searchText.isEmpty()
            if (searchText.isNotEmpty()) {
                onSubmitSearch.invoke(searchText)
                focusManager.clearFocus()
            }
        }
    }

    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
    }

}


@Preview(showBackground = true)
@Composable
fun SearchPageTopBarPreview() {
    BoxOfficeTheme {
        SearchPageTopBar("", {}) {}
    }
}
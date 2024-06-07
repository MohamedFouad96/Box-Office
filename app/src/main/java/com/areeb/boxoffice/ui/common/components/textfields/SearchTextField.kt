package com.areeb.boxoffice.ui.common.components.textfields

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.R
import com.areeb.boxoffice.ui.theme.Arsenic
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme
import com.areeb.boxoffice.ui.theme.DimGray


@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    searchText: String,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = searchText,
        onValueChange = onValueChange,

        trailingIcon = { Icon(imageVector = Icons.Outlined.Search , tint = DimGray, contentDescription = "")  },
        isError = isError,
        supportingText = { if (isError) Text(stringResource(id = R.string.search_title_required)) },
        keyboardActions = KeyboardActions(onSearch = {
            focusManager.clearFocus()
            onSearch.invoke()
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        placeholder = { Text(text = stringResource(id = R.string.search), style = MaterialTheme.typography.titleSmall.copy(color = DimGray))},
        textStyle = MaterialTheme.typography.titleSmall,
        shape = CircleShape.copy(CornerSize(16.dp)),
        colors = TextFieldDefaults.colors(unfocusedContainerColor = Arsenic, focusedContainerColor = Arsenic,)
    )
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SearchTextFieldPreview() {
    BoxOfficeTheme {
        SearchTextField(Modifier,"", false, {}, {})
    }
}
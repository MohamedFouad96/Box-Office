package com.areeb.boxoffice.ui.common.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.ui.common.BaseViewModel
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme
import com.areeb.boxoffice.ui.common.components.feedback.DefaultAppLoading
import com.areeb.boxoffice.ui.common.components.feedback.SnackBarContainer


@Composable
fun DefaultContainer(
    modifier: Modifier = Modifier,
    color: Color? = null,
    viewModel: BaseViewModel? = null,
    content: @Composable () -> Unit,
) {

    val isLoading by remember { viewModel?.showLoading ?: mutableStateOf(false) }

    val currentColor = color ?:  MaterialTheme.colorScheme.background
    Box(modifier = modifier.wrapContentHeight().background(currentColor)) {
        if(isLoading)
            modifier.clickable(enabled = false) {}


        SnackBarContainer(viewModel, currentColor) {

            CompositionLocalProvider(LocalAbsoluteTonalElevation provides 0.dp) {
                content()
            }

        }


        if (isLoading)
            DefaultAppLoading(currentColor)
    }


}



@Preview
@Composable
fun BackgroundDefault() {
    BoxOfficeTheme() {
        DefaultContainer(modifier = Modifier
            .width(30.dp)
            .height(30.dp),content = {})
    }
}

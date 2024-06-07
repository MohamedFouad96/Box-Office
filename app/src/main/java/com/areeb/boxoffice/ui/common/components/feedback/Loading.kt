package com.areeb.boxoffice.ui.common.components.feedback

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.areeb.boxoffice.ui.theme.BlueCola
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme


@Composable
fun DefaultAppLoading(color: Color) {

        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
            .background(color = color.copy(alpha = 0.6f))
            .pointerInput(Unit) {

            }, ) {
            CircularProgressIndicator(color = BlueCola)
        }



}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_MASK)
@Composable
fun DefaultAppLoadingPreview() {
    BoxOfficeTheme {
        DefaultAppLoading(MaterialTheme.colorScheme.background)
    }
}
package com.areeb.boxoffice.ui.common.components.feedback

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.areeb.boxoffice.ui.theme.BlueCola
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme


@Composable
fun DefaultCircularProgressIndicator(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.size(30.dp)) {
        CircularProgressIndicator(modifier = modifier, strokeWidth = 2.dp, color = Color.Black, trackColor = BlueCola)
    }
}


@Preview(showBackground = true,uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun DefaultCircularProgressIndicatorPreview() {
    BoxOfficeTheme {
        DefaultCircularProgressIndicator()
    }
}
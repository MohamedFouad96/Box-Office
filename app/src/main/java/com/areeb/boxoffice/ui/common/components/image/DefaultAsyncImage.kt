package com.areeb.boxoffice.ui.common.components.image

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme
import com.areeb.boxoffice.ui.theme.DimGray
import com.areeb.boxoffice.ui.common.components.feedback.DefaultCircularProgressIndicator


@SuppressLint("SuspiciousIndentation")
@Composable
fun DefaultAsyncImage(modifier: Modifier = Modifier,imageUrl: String) {

    var isLoading = rememberSaveable { mutableStateOf(true) }


            Box(
                modifier = modifier.background(DimGray),
                        contentAlignment = Alignment.Center

            ) {

                if (isLoading.value)
                    DefaultCircularProgressIndicator()


                AsyncImage(modifier = Modifier.matchParentSize(),model = imageUrl, contentDescription = "", contentScale = ContentScale.Crop, onState = {
                    if (it is AsyncImagePainter.State.Success) {
                        isLoading.value = false
                    }
                })

            }



}


@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_MASK
)
@Composable
fun EventItemPreview() {
    BoxOfficeTheme {
        DefaultAsyncImage(imageUrl = "")
    }
}
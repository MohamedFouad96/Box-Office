package com.areeb.boxoffice.ui.main.tabs.home.components.top_rated_list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.areeb.boxoffice.ui.theme.BlueCola
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme
import com.areeb.boxoffice.ui.theme.CharlestonGreen
import com.areeb.boxoffice.ui.theme.poppinsFamily


@Composable
fun DefaultNumericOutlinedText(modifier: Modifier = Modifier,text: String) {
    Text(modifier = modifier,text = text,style = TextStyle.Default.copy(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.SemiBold,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
        fontSize = 96.sp,
        color = CharlestonGreen,
        shadow = Shadow(color = BlueCola, blurRadius = 5f)
    ))
}

@Preview(showBackground = true, backgroundColor =0xFF242A32)
@Composable
fun HomePageTopBarPreview() {
    BoxOfficeTheme {
        DefaultNumericOutlinedText(text ="2")
    }
}
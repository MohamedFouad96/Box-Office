package com.areeb.boxoffice.ui.common.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import  com.areeb.boxoffice.R
import com.areeb.boxoffice.ui.theme.BoxOfficeTheme

@Composable
fun ErrorState(message: String? = null, onRetry: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.ic_warning), contentDescription = "")
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = message ?: stringResource(id = R.string.sorry_there_appears_to_be_a_system_problem), style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center))


        Spacer(modifier = Modifier.height(23.dp))

        Button(modifier = Modifier
            .height(45.dp), shape = RoundedCornerShape(5.dp), onClick = {
            onRetry.invoke()
        }, colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary

        )
        ) {
            Text(text = stringResource(id = R.string.retry), style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSecondary))
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_MASK)
@Composable
fun ErrorStatePreview() {
    BoxOfficeTheme {
        ErrorState {}
    }
}
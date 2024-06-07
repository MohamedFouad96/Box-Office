package com.areeb.boxoffice.ui.common.components.feedback

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.areeb.boxoffice.ui.common.BaseViewModel
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SnackBarContainer(viewModel: BaseViewModel? = null, color: Color, content: @Composable () -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }


    if (viewModel != null) {

        Scaffold(
            containerColor = color,
            snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },) {
            content()
        }

    } else {
        content()
    }


    LaunchedEffect(viewModel?.showSnackBarError) {
        viewModel?.showSnackBarError?.collectLatest {
            if (it.isNotEmpty()) {
                snackbarHostState.showSnackbar(it)
            }
        }
    }

    LaunchedEffect(viewModel?.showSnackBarError) {
        viewModel?.showSnackBar?.collectLatest {
            if (it.isNotEmpty()) {
                snackbarHostState.showSnackbar(it)
            }
        }
    }


}
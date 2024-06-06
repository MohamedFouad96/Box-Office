package com.areeb.boxoffice.ui.common


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow



abstract class BaseViewModel() : ViewModel(),DefaultLifecycleObserver {


    val showLoading = mutableStateOf(false)
    val showSnackBar = MutableSharedFlow<String>()
    val showSnackBarError = MutableSharedFlow<String>()
    val showNoData = MutableSharedFlow<Boolean>()


}
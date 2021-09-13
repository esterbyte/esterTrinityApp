package com.ester.esterTrinityApp.presentation


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {
    val scope = CoroutineScope(
        Job() + Dispatchers.Main
    )
}
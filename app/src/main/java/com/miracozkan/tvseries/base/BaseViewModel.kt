package com.miracozkan.tvseries.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 25.08.2019 - 10:11            │
//└─────────────────────────────┘

abstract class BaseViewModel : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main
    val scope = CoroutineScope(coroutineContext)

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }
}
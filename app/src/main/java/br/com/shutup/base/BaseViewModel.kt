package br.com.shutup.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {

    fun getExceptionHandler(callback: (e: Throwable) -> Unit) = CoroutineExceptionHandler { _, exception ->
        callback.invoke(exception)
    }
}
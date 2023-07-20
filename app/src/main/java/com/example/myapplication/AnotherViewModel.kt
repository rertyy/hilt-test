package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class AnotherViewModel @Inject constructor(
    @Named("repository") private val repository: Lazy<MyRepository>,
    private val SharedPreferencesManager: SharedPreferencesManager,
) : ViewModel() {

    private val tokenFlow = SharedPreferencesManager.getTokenFlow()
    private var jwtToken = ""

    fun readToken2() {
        viewModelScope.launch {
            try {
                coroutineScope {
                    tokenFlow.collect { token ->
                        jwtToken = token
                        Log.d("AnotherViewModel", "x is $jwtToken")
                        currentCoroutineContext().cancel()
                    }
                }
            } catch (e: Exception) {
                Log.d("MyViewModel", "Error: ${e.message}")
            }
        }
    }
}
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
class MyViewModel @Inject constructor(
    @Named("repository") private val repository: Lazy<MyRepository>,
    private val SharedPreferencesManager: SharedPreferencesManager,
) : ViewModel() {

    init {
        repository.get()
        Log.d("MyViewModel", "Hello from MyViewModel")
    }

    private var jwtToken = "invalid token"

    private suspend fun saveToken(jwtToken: String) {
        SharedPreferencesManager.storeJwtToken(jwtToken)
        Log.d("MyViewModel", "Hello from saveToken")
    }

    private suspend fun readToken() {
        Log.d("MyViewModel", "Hello from readToken. Reading. Token is $jwtToken")
        val tokenFlow = SharedPreferencesManager.getTokenFlow()
        Log.d("MyViewModel", "token flow: $tokenFlow.toString()")
        val job = coroutineScope {
            tokenFlow.collect { token ->
                jwtToken = token
                Log.d("MyViewModel", "x is $jwtToken")
                currentCoroutineContext().cancel()
            }
        }
    }


    fun getThreads() {
        viewModelScope.launch {
            try {
                val threads: String? = repository.get().getThreads().body()
                Log.d("GET THREADS", "threads: $threads")
                threads?.let { saveToken(it) }
                readToken()

            } catch (e: Exception) {
                Log.d("MyViewModel", "Error: ${e.message}")
            }
        }

    }

    fun refreshThreads() {
        viewModelScope.launch {
            try {
                readToken()
            } catch (e: Exception) {
                Log.d("MyViewModel", "Error: ${e.message}")
            }
        }
    }


}
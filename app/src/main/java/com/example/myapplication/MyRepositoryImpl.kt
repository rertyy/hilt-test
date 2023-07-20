package com.example.myapplication

import android.app.Application
import android.util.Log
import retrofit2.Response
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val api: MyApi,
    appContext: Application
) : MyRepository {

    init {
        val appName = appContext.getString(R.string.app_name)
        Log.d("TAG", "Hello from the repository. The app name is $appName")
    }

    override suspend fun getThreads(): Response<String> {
        return api.getThreads(LoginRequest("user3", "password"))
    }
}
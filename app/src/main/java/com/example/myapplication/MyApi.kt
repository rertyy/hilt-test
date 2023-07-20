package com.example.myapplication

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApi {
    @POST("/user/login")
    suspend fun getThreads(
        @Body request: LoginRequest
    ): Response<String>
}


data class LoginRequest(
    val username: String,
    val password: String
)
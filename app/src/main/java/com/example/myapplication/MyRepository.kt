package com.example.myapplication

import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class Threads(
    @SerializedName("thread_id") val threadID: Int,
    @SerializedName("thread_name") val threadName: String,
    @SerializedName("thread_body") val threadBody: String,
    @SerializedName("thread_created_by") val threadCreatedBy: Int,
    @SerializedName("thread_created_at") val threadCreatedAt: String,
    @SerializedName("user_id") val userID: Int,
    @SerializedName("username") val username: String
)


interface MyRepository {
    suspend fun getThreads(): Response<String>
}
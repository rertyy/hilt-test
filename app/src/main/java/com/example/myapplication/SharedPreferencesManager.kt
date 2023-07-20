package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton


// https://developer.android.com/topic/libraries/architecture/datastore
// https://developer.android.com/reference/kotlin/androidx/datastore/package-summary

@Singleton
class SharedPreferencesManager(context: Context) {

    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
        private val ACCESS_TOKEN = stringPreferencesKey("jwt_token")
        val Context.dataStore by preferencesDataStore(
            name = USER_PREFERENCES_NAME
        )

    }


    private val datastore = context.dataStore

    suspend fun storeJwtToken(accessToken: String) {
        datastore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
        }
        Log.d("GET THREAD", "STORED IN DATASTORE $accessToken")
    }

    fun getTokenFlow(): Flow<String> {
        return datastore.data.map { preferences ->
            preferences[ACCESS_TOKEN] ?: "token absent"
        }

    }

}


package com.example.android.challenge.api

import android.util.Log
import com.example.android.challenge.model.Content
import com.example.android.challenge.model.UserContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.lang.Exception

private const val TAG = "ContentsService"

fun getContents(
        service: ContentsService,
        onSuccess: (contents: List<Content>) -> Unit,
        onError: (error: String) -> Unit
) {

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = service.getContents()
            Log.d(TAG, "got a response $response")
            if (response.isSuccessful) {
                val contents = response.body()?.items ?: emptyList()
                onSuccess(contents)
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (t: Exception) {
            Log.d(TAG, "fail to get data")
            onError(t.message ?: "unknown error")
        }
    }


}

interface ContentsService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun getContents(
    ): Response<UserContent>

    companion object {
        private const val BASE_URL = "https://dl.dropboxusercontent.com/"

        fun create(): ContentsService {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ContentsService::class.java)
        }
    }
}
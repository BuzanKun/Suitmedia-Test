package com.collection.suitmediatest.data.remote.retrofit

import com.collection.suitmediatest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            val authInterceptor = okhttp3.Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(
                        BuildConfig.REQRES_API_HEADER,
                        BuildConfig.REQRES_API_KEY
                    )
                    .build()
                chain.proceed(request)
            }
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_REQRES_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
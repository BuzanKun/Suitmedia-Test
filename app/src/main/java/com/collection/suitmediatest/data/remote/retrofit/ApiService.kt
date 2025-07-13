package com.collection.suitmediatest.data.remote.retrofit

import com.collection.suitmediatest.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): UserResponse
}
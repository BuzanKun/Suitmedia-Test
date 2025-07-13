package com.collection.suitmediatest.data.remote

import com.collection.suitmediatest.data.remote.model.User
import com.collection.suitmediatest.data.remote.model.toUser
import com.collection.suitmediatest.data.remote.retrofit.ApiConfig
import com.collection.suitmediatest.data.remote.retrofit.ApiService

@Suppress("UNCHECKED_CAST")
class UserRepository(
    private val apiService: ApiService
) {
    suspend fun getUsers(page: Int, perPage: Int): Result<List<User>> {
        return try {
            val response = apiService.getUsers(page = page, perPage = perPage)
            val users = response.data?.map { it?.toUser() } ?: emptyList()
            Result.success(users)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        } as Result<List<User>>
    }

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null
        fun getInstance(apiService: ApiService): UserRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserRepository(apiService)
            }.also { INSTANCE = it }
    }
}

object UserInjection {
    fun provideRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}
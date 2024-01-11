package com.example.composeretrofitmvvmroomcode.network

import com.example.composeretrofitmvvmroomcode.model.UserData
import retrofit2.Response

class DataSource
{
    private val apiService = ApiRestClient().getClient()!!.create(APIService::class.java)

    suspend fun userData(): Response<UserData> {
        return apiService.userData()
    }

}
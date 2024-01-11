package com.example.composeretrofitmvvmroomcode.network

import com.example.composeretrofitmvvmroomcode.model.UserData
import retrofit2.Response
import retrofit2.http.GET


interface APIService
{
    @GET("products")
    suspend fun userData(): Response<UserData>

}
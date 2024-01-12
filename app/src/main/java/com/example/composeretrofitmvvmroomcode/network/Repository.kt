package com.example.composeretrofitmvvmroomcode.network

import android.widget.Toast
import com.example.composeretrofitmvvmroomcode.model.UserData
import kotlin.coroutines.coroutineContext

class Repository
{
    private val dataSource: DataSource = DataSource()

    suspend fun userData(): Resource<UserData> {
        return try {
            val response = dataSource.userData()
                if (response.isSuccessful) {
                    Resource.success(response.body()!!)
                }else {
                    Resource.error(data = response.body(), message = "")
                }

        } catch (e: Exception) {
            //CommonExceptionHandle.instance.handle(e)
            Resource.exception(message = "error:"+ e.message!!)
        }
    }

}
package com.example.composeretrofitmvvmroomcode.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.composeretrofitmvvmroomcode.model.UserData
import com.example.composeretrofitmvvmroomcode.network.Repository
import com.example.composeretrofitmvvmroomcode.network.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommonViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository = Repository()
    private val userData: MutableLiveData<Resource<UserData>> = MutableLiveData()

    val userDataResp: MutableLiveData<Resource<UserData>>
        get() = userData

    private val dataResp = MutableStateFlow<Resource<UserData>>(Resource.default())

    //To observable
    //val data:StateFlow<UserData> = dataResp

    val data : StateFlow<Resource<UserData>> = dataResp.asStateFlow()

    fun userData() {
        viewModelScope.launch {
            //userData.postValue(Resource.loading(data = null))
            val response = repository.userData()
            Log.v("respoonse", "respoonse " + response)
            dataResp.value = response
            //userData.postValue(response)
        }

    }

}
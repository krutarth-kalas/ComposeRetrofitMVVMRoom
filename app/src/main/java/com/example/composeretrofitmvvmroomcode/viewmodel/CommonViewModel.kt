package com.example.composeretrofitmvvmroomcode.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.composeretrofitmvvmroomcode.model.UserData
import com.example.composeretrofitmvvmroomcode.network.Repository
import com.example.composeretrofitmvvmroomcode.network.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommonViewModel(application: Application) : AndroidViewModel(application),DefaultLifecycleObserver {
    private val repository: Repository = Repository()
    private val _showLoader = MutableStateFlow(true)
    val showLoader : StateFlow<Boolean> = _showLoader.asStateFlow()

    private val dataResp = MutableStateFlow<Resource<UserData>>(Resource.default())

    val data : StateFlow<Resource<UserData>> = dataResp.asStateFlow()


    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        userData()
    }


    private fun userData() {
        viewModelScope.launch {
            val response = repository.userData()
            _showLoader.value = false
            dataResp.value = response
        }
    }
}
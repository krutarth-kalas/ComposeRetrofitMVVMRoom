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
import androidx.room.Room
import com.example.composeretrofitmvvmroomcode.database.AppDatabase
import com.example.composeretrofitmvvmroomcode.database.ProductEntity
import com.example.composeretrofitmvvmroomcode.model.Product
import com.example.composeretrofitmvvmroomcode.model.UserData
import com.example.composeretrofitmvvmroomcode.network.Repository
import com.example.composeretrofitmvvmroomcode.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommonViewModel(private val application: Application) : AndroidViewModel(application),DefaultLifecycleObserver {
    private val repository: Repository = Repository()
    private val _showLoader = MutableStateFlow(true)
    val showLoader : StateFlow<Boolean> = _showLoader.asStateFlow()
    private val _dataResp = MutableStateFlow<Resource<UserData>>(Resource.default())
    val data : StateFlow<Resource<UserData>> = _dataResp.asStateFlow()
    private lateinit var resp : Resource<UserData>
    val database = AppDatabase.getInstance(application)

    private lateinit var list : Resource<ProductEntity>


    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        userData()
    }


    private fun userData() {
        viewModelScope.launch {
             resp = repository.userData()
            _showLoader.value = false
            val products= resp.data?.products
            products?.forEach {
                val productEntity= ProductEntity(
                    id=it.id?:0,
                    brand = it.brand?:"",
                    price = it.price?:0,
                    category = it.category?:"",
                    description = it.description?:"",
                    rating = it.rating?:0.00,
                    thumbnail = it.thumbnail?:"",
                    title = it.title?:""
                )
                database.productDao().insert(productEntity)
            }
            _dataResp.value = resp

            //getProdList()
        }
    }

    suspend fun getProdList()
    {
        withContext(Dispatchers.IO){
          database.productDao().getAllProduct().collect({
              list = it as Resource<ProductEntity>
          })
        }

    }
}
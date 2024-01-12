package com.example.composeretrofitmvvmroomcode.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeretrofitmvvmroomcode.R
import com.example.composeretrofitmvvmroomcode.model.DataClass
import com.example.composeretrofitmvvmroomcode.model.Product
import com.example.composeretrofitmvvmroomcode.model.UserData
import com.example.composeretrofitmvvmroomcode.ui.theme.ComposeRetrofitMvvmRoomCodeTheme
import com.example.composeretrofitmvvmroomcode.viewmodel.CommonViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(viewModel : CommonViewModel = viewModel())
{
    //Create instance of Lifecycle
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(key1 = lifecycle)
    {
        lifecycle.addObserver(viewModel)
        onDispose {
            lifecycle.removeObserver(viewModel)
        }
    }

    ComposeRetrofitMvvmRoomCodeTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title =
                    {
                        Text(text = "MY LIST", textAlign = TextAlign.Center)
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Menu")
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                )
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(vertical = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                val users by viewModel.data.collectAsState()
                //val users by viewModel.dataNew.collectAsState()

                val showLoader by viewModel.showLoader.collectAsState()

                if (showLoader){
                    Column(
                        modifier = Modifier.fillMaxSize().padding(vertical = 100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }else{
                    Recyclerview(users = users.data?.products ?: listOf())
                }
            }
        }
    }
}


@Composable
private fun Recyclerview(users : List<Product>){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(users.size){index ->
            recyclerviewData(users[index])
        }
    }
}

@Composable
private fun recyclerviewData(user: Product){
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(CornerSize(10.dp))
    ) {
        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                modifier = Modifier
                    .padding(8.dp)
                    .size(80.dp)
                    .align(Alignment.CenterVertically)
                    .clip(
                        RoundedCornerShape(CornerSize(10.dp))
                    ))
            Text(text = user.title.toString(), modifier = Modifier.padding(8.dp))
        }
    }
}
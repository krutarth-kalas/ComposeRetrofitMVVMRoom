package com.example.composeretrofitmvvmroomcode.ui.composable

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composeretrofitmvvmroomcode.model.Product
import com.example.composeretrofitmvvmroomcode.navigation.NavigationFactory
import com.example.composeretrofitmvvmroomcode.ui.theme.ComposeRetrofitMvvmRoomCodeTheme
import com.example.composeretrofitmvvmroomcode.viewmodel.CommonViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(context: Context, navHostController: NavHostController, viewModel: CommonViewModel)
{

//Create instance of Lifecycle
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(key1 = lifecycle)   {
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
                        modifier = Modifier.fillMaxSize().padding(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(/*progress = 0.5f, */color = Color.Yellow)
                    }
                }else{
                    Recyclerview(users = users.data?.products ?: listOf(),context,navHostController)
                }
            }
        }
    }
}

@Composable
private fun Recyclerview(users: List<Product>, context: Context, navHostController: NavHostController)
{
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(users.size){index ->
            recyclerviewData(users[index],context,navHostController)
        }
    }
}

@Composable
private fun recyclerviewData(product: Product, context: Context, navHostController: NavHostController){
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(), shape = RoundedCornerShape(
            CornerSize(10.dp)
        )
    ) {
        Row(
            modifier = Modifier.padding(5.dp).clickable {
                //navHostController.navigate(NavigationFactory.DetailsScreen.route)
                //navHostController.navigate(NavigationFactory.DetailsScreen.createRoute(user))
                navHostController.currentBackStackEntry?.savedStateHandle?.set("user",product)
                /*navHostController.currentBackStackEntry?.arguments?.apply {
                    putParcelable("USER", product)
                }*/
                navHostController.navigate(NavigationFactory.DetailsScreen.route)/*{
                    popUpTo(NavigationFactory.DetailsScreen.route){
                        inclusive = true
                    }
                }*/
            }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.thumbnail)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(128.dp)
                    .padding(16.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Black, CircleShape)
            )
            Text(text = product.title.toString(), modifier = Modifier.padding(8.dp))
        }
    }
}
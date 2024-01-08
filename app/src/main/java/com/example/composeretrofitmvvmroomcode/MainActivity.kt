package com.example.composeretrofitmvvmroomcode

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeretrofitmvvmroomcode.model.DataClass
import com.example.composeretrofitmvvmroomcode.model.dummyData
import com.example.composeretrofitmvvmroomcode.ui.theme.ComposeRetrofitMvvmRoomCodeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                     /*
                     val vm = viewModel<MainViewModel>()
                     private val viewModel by viewModels<MainViewModel>()
                      mainActivityViewModel: MainActivityViewModel =viewModel(),
                     val dashboardViewModel: DashboardViewModel = hiltViewModel()
                       val uiState= dashboardViewModel.productUIState.collectAsState()
                       https://bigknol.com/jetpack-compose/viewmodel-jetpack-compose-android-simple-example/

                       val productList=uiState.value.productList
                       val isLoading=uiState.value.isLoading*/
                   }
                }
            }
        }
    }
}

@Composable
fun Recyclerview(users : List<DataClass>){
    LazyColumn() {
        items(users.size){userData ->
            //recyclerviewData(userData)
        }
    }
}

@Composable
fun recyclerviewData(user: DataClass){
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
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
            Text(text = user.name, modifier = Modifier.padding(8.dp))
        }
    }
}
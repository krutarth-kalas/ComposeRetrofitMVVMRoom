package com.example.composeretrofitmvvmroomcode

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.rememberNavController
import com.example.composeretrofitmvvmroomcode.ui.composable.MainContent
import com.example.composeretrofitmvvmroomcode.viewmodel.CommonViewModel

class MainActivity : ComponentActivity() {
    private val mainActivityViewModel: CommonViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            MainContent(this,navHostController, mainActivityViewModel)
        }
    }
}
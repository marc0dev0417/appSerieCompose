package com.marc0dev.compose1.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.marc0dev.compose1.viewmodels.CharacterViewModel
import com.marc0dev.compose1.viewmodels.isInternetAvailable
import com.marc0dev.compose1.views.CardList
import com.marc0dev.compose1.views.TextNotConnection

@Composable
fun MainScreen(viewModel: CharacterViewModel, navController: NavHostController){
        if(viewModel.connection){
            CardList(viewModel = viewModel, navHostController = navController)
        }else{
            TextNotConnection()
        }
}
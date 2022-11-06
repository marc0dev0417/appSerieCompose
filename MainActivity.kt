package com.marc0dev.compose1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign


import androidx.compose.ui.unit.dp
import com.marc0dev.compose1.navigation.Navigation

import com.marc0dev.compose1.viewmodels.CharacterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val characterViewModel: CharacterViewModel = CharacterViewModel(context = this)
        characterViewModel.checkConnection(this)

      setContent {
          Navigation(viewModel = characterViewModel)
      }
    }
}






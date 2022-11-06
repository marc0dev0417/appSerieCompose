package com.marc0dev.compose1.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TextNotConnection() {
           Box( modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {
               Text(text = "Not Connection")
           }
}
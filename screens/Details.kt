package com.marc0dev.compose1.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun Details(id: Int, name: String, image: String){

    val idItem = remember { id }
    val nameItem = remember { name }//Recovery one time!!
    val imageItem = remember { image }

   // Log.d("see", imageItem)

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Column {

            Image(
                painter = rememberAsyncImagePainter(
                    model = imageItem),
                contentDescription = nameItem,
                modifier = Modifier.size(200.dp)
            )
            /*
            Text(text = idItem.toString())
            Text(text = nameItem)
            Text(text = imageItem)
             */
        }


    }
}
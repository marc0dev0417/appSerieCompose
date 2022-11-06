package com.marc0dev.compose1.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.marc0dev.compose1.R

import com.marc0dev.compose1.viewmodels.CharacterViewModel
import com.marc0dev.compose1.viewmodels.isInternetAvailable
import kotlinx.coroutines.delay
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun CardList(viewModel: CharacterViewModel, navHostController: NavHostController){
    val context = LocalContext.current

    var isRefreshing: Boolean by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(isRefreshing){
        if(isRefreshing){
        delay(2000)
        isRefreshing = false
        }
    }

    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = { isRefreshing = true }) {

        if (isInternetAvailable(context)) {
            LazyColumn() {
                item { TopBar() }
                items(viewModel.characters.results) { item ->
                    Spacer(modifier = Modifier.size(12.dp))
                    Box(modifier = Modifier
                        .fillMaxSize()
                    , contentAlignment = Alignment.Center
                    ){
                        Card(
                            modifier = Modifier
                                .clickable {
                                    val encodeURl =
                                        URLEncoder.encode(
                                            item.image,
                                            StandardCharsets.UTF_8.toString()
                                        )
                                    navHostController.navigate("details/${item.id}/${item.name}/${encodeURl}")
                                }
                                .border(2.dp, MaterialTheme.colors.secondary)
                                .size(300.dp),
                            backgroundColor = Color.LightGray
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(
                                        16.dp
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally

                            ) {
                                Text(text = item.name!!, textAlign = TextAlign.Center, style = MaterialTheme.typography.h5)
                                // Text(text = item.image!!)
                                Row(modifier = Modifier
                                    .padding(20.dp)
                                ) {
                                    when(item.status?.toLowerCase(Locale.current)){
                                        "alive" -> {
                                            Text(text = item.status, style = MaterialTheme.typography.h6)
                                            Image(painter = painterResource(id = R.drawable.active), contentDescription = "This guy is alive", modifier = Modifier.size(30.dp))
                                        }
                                        "unknown" -> {
                                            Text(text = item.status, style = MaterialTheme.typography.h6)
                                            Image(painter = painterResource(id = R.drawable.clue), contentDescription = "This guy is unknown", modifier = Modifier.size(30.dp))
                                        }
                                        "dead" -> {
                                            Text(text = item.status, style = MaterialTheme.typography.h6)
                                            Image(painter = painterResource(id = R.drawable.dead), contentDescription = "This guy is dead", modifier = Modifier.size(30.dp))
                                        }

                                    }
                                }
                                Image(painter = rememberAsyncImagePainter(model = item.image), contentDescription = item.id.toString())
                            }
                        }
                    }

                }
            }
        }
    }
}

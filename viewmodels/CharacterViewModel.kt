package com.marc0dev.compose1.viewmodels

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.marc0dev.compose1.models.Person
import com.marc0dev.compose1.models.Result

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.coroutines.launch


class CharacterViewModel(context: Context): ViewModel() {
    private val gson = Gson()

    var characters: Person by mutableStateOf(
        Person(
            info = null,
            results = listOf(Result(null, "", "",""))
        )
    )
    var connection: Boolean by mutableStateOf(false)

    init {
        checkConnection(context = context)
        viewModelScope.launch {

            if(connection) fetchCharacters()

            }
        }
    suspend fun fetchCharacters() {
        val client = HttpClient() {
            install(ContentNegotiation.toString()) {
                Gson()
            }
        }
        val response: HttpResponse = client.get("https://rickandmortyapi.com/api/character")
        val listCharacter: Person = gson.fromJson(response.bodyAsText(), Person::class.java)

        characters = listCharacter

       // Log.d("object", characters.results[3].image.toString())
    }
    fun checkConnection(context: Context) {
        Thread(Runnable {
            while (true) {
                connection = isInternetAvailable(context)
            }
        }).start() // Starting the thread
    }
    }
fun isInternetAvailable(context: Context): Boolean {
    var result = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNw =
        connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
    result = when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }

    return result
}


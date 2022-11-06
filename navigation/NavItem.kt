package com.marc0dev.compose1.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    val navArgs: List<CharacterArg> = emptyList()
){
    val route = run {
        //baseRoute/{arg1}/{arg2}/{arg3}...
        val argsKey = navArgs.map {
            item ->
            "{${item.key}}"
        }
        //Concat base then key arg with separator "/"
        listOf(baseRoute)
            .plus(argsKey)
            .joinToString("/")
    }

    //Arguments to pass in navigation
    val args = navArgs.map {
        navArgument(it.key){type = it.navType}
    }

    //Create object to use in path with key argument
    object Main: NavItem("main")
    object Detail: NavItem(
        "details",
        listOf(CharacterArg.ID, CharacterArg.NAME, CharacterArg.IMAGE))
}

enum class CharacterArg(val key: String, val navType: NavType<*>){
    ID("id", navType = NavType.IntType),
    NAME("name", navType = NavType.StringType),
    IMAGE("image", navType = NavType.StringType)
}

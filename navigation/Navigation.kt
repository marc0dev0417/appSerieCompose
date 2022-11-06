package com.marc0dev.compose1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.marc0dev.compose1.screens.Details
import com.marc0dev.compose1.screens.MainScreen
import com.marc0dev.compose1.viewmodels.CharacterViewModel

@Composable
fun Navigation(viewModel: CharacterViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController,
            startDestination = NavItem.Main.route){
        composable(NavItem.Main.route){
            MainScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = NavItem.Detail.route,
            arguments = NavItem.Detail.args){
                backStackEntry ->
                val id: Int? = backStackEntry.arguments?.getInt("id")
                val name: String? = backStackEntry.arguments?.getString("name")
                val image: String? = backStackEntry.arguments?.getString("image")
                Details(id = id!!, name = name!!, image = image!!)
        }
    }
}
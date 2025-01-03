package com.example.mc_retete_culinare.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mc_retete_culinare.Greeting
import com.example.mc_retete_culinare.HomeDestination
import com.example.mc_retete_culinare.ui.recipe.RecipeEntryDestination
import com.example.mc_retete_culinare.ui.recipe.RecipeEntryScreen

@Composable
fun RecipeNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController, startDestination = HomeDestination.route, modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            Greeting(
                name = "Recipe",
                navigateToItemEntry = { navController.navigate(RecipeEntryDestination.route) },
                navigateToItemUpdate = {
                    navController.navigate("${RecipeDetailsDestination.route}/${it}")
                })
        }
        composable(route = RecipeEntryDestination.route) {
            RecipeEntryScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
    }
}
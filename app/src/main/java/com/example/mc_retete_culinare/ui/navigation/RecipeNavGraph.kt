package com.example.mc_retete_culinare.ui.navigation

import RecipeDetailsScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mc_retete_culinare.Greeting
import com.example.mc_retete_culinare.HomeDestination
import com.example.mc_retete_culinare.ui.recipe.RecipeEntryDestination
import com.example.mc_retete_culinare.ui.recipe.RecipeEntryScreen
import com.example.mc_retete_culinare.ui.recipe.RecipeEditDestination
import com.example.mc_retete_culinare.ui.recipe.RecipeEditScreen


@Composable
fun RecipeNavHost(
    navController: NavHostController,
    firstRecipeId: Int?,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController, startDestination = HomeDestination.route, modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            Greeting(
                name = "Recipe",
                navigateToItemEntry = { navController.navigate(RecipeEntryDestination.route) },
                navigateToFirstRecipe = {
                    firstRecipeId?.let { id ->
                        navController.navigate("${RecipeDetailsDestination.route}/${id}")
                    }
                }
            )
        }

        composable(route = RecipeEntryDestination.route) {
            RecipeEntryScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }

        composable(
            route = RecipeDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(RecipeDetailsDestination.recipeIdArg) {
                type = NavType.IntType
            })
        ) {
            RecipeDetailsScreen(
                navigateToEditRecipe = { recipeId ->
                    navController.navigate("${RecipeEditDestination.route}/$recipeId")
                },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(
            route = RecipeEditDestination.routeWithArgs,
            arguments = listOf(navArgument(RecipeEditDestination.recipeIdArg) {
                type = NavType.IntType
            })
        ) {
            RecipeEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
    }
}
package com.example.mc_retete_culinare.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mc_retete_culinare.R
import com.example.mc_retete_culinare.ui.AppViewModelProvider
import com.example.mc_retete_culinare.ui.recipe.OfflineRecipeDetailsViewModel
import com.example.mc_retete_culinare.ui.screens.HomeScreen
import com.example.mc_retete_culinare.ui.recipe.OnlineRecipeDetailsViewModel
import com.example.mc_retete_culinare.ui.screens.OfflineRecipesViewModel
import com.example.mc_retete_culinare.ui.screens.OnlineRecipesViewModel
import com.example.mc_retete_culinare.ui.screens.RecipeItem
import com.example.mc_retete_culinare.ui.screens.RecipeUiState
import com.example.mc_retete_culinare.ui.screens.RecipesList
import com.example.mc_retete_culinare.ui.screens.toRecipeDetails
import kotlinx.coroutines.launch

@Composable
fun RecipeNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val onlineRecipesViewModel: OnlineRecipesViewModel =
        viewModel(factory = AppViewModelProvider.Factory)

    val onlineRecipeDetailsViewModel: OnlineRecipeDetailsViewModel =
        viewModel(factory = AppViewModelProvider.Factory)

    val offlineRecipeDetailsViewModel: OfflineRecipeDetailsViewModel =
        viewModel(factory = AppViewModelProvider.Factory)

    val offlineRecipesViewModel: OfflineRecipesViewModel =
        viewModel(factory = AppViewModelProvider.Factory)

    val coroutineScope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = RecipesScreen.OnlineRecipesScreen.name,
        modifier = modifier
    ) {

        composable(route = RecipesScreen.OnlineRecipesScreen.name) {
            HomeScreen(
                recipeUiState = onlineRecipesViewModel.onlineRecipesState,
                retryAction = onlineRecipesViewModel::getOnlineRecipes,
                navigate = { recipe ->
                    onlineRecipeDetailsViewModel.currentOnlineRecipe = recipe
                    navController.navigate(RecipesScreen.OnlineRecipeDetailsScreen.name)
                }
            )
        }

        composable(route = RecipesScreen.OnlineRecipeDetailsScreen.name) {
            RecipeItem(
                recipe = onlineRecipeDetailsViewModel.currentOnlineRecipe,
                isExpandedView = true,
                showSaveButton = true,
                showDeleteButton = false,
                onRecipeCardClicked = {},
                onDeleteButtonClicked = {},
                onSaveButtonClicked = {
                    coroutineScope.launch {
                        offlineRecipesViewModel.recipeUiState = RecipeUiState(
                            onlineRecipeDetailsViewModel.currentOnlineRecipe.toRecipeDetails()
                        )
                        offlineRecipesViewModel.addRecipe()
                        navController.navigate(RecipesScreen.OfflineRecipesScreen.name)
                    }
                },
                modifier = Modifier.padding(
                    dimensionResource(R.dimen.padding_small)
                )
            )
        }

        composable(route = RecipesScreen.OfflineRecipesScreen.name) {
            val recipesListState by offlineRecipesViewModel.offlineRecipes.collectAsState()

            RecipesList(
                recipes = recipesListState.recipeList,
                navigate = { recipe ->
                    offlineRecipeDetailsViewModel.currentOfflineRecipe = recipe
                    navController.navigate(RecipesScreen.OfflineRecipeDetailsScreen.name)
                },
                modifier = modifier
            )
        }

        // TODO overflow instructiuni rezolvat
        composable(route = RecipesScreen.OfflineRecipeDetailsScreen.name) {
            RecipeItem(
                recipe = offlineRecipeDetailsViewModel.currentOfflineRecipe,
                isExpandedView = true,
                showSaveButton = false,
                showDeleteButton = true,
                onRecipeCardClicked = {},
                onSaveButtonClicked = {},
                onDeleteButtonClicked = {
                    coroutineScope.launch {
                        offlineRecipesViewModel.recipeUiState = RecipeUiState(
                            offlineRecipeDetailsViewModel.currentOfflineRecipe.toRecipeDetails()
                        )
                        offlineRecipesViewModel.deleteRecipe()
                        navController.navigate(RecipesScreen.OfflineRecipesScreen.name)
                }},
                modifier = Modifier.padding(
                    dimensionResource(R.dimen.padding_small)
                )
            )
        }
    }
}
package com.example.mc_retete_culinare.ui.recipe

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mc_retete_culinare.R
import com.example.mc_retete_culinare.ui.AppViewModelProvider
import com.example.mc_retete_culinare.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object RecipeEditDestination : NavigationDestination {
    override val route = "recipe_edit"
    override val titleRes = R.string.edit_recipe_title
    const val recipeIdArg = "recipeId"
    val routeWithArgs = "$route/{${recipeIdArg}}"
}

@Composable
fun RecipeEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RecipeEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Card(
        modifier = modifier
    ) {
        RecipeEntryBody(
            recipeUiState = viewModel.recipeUiState,
            onRecipeValueChange = { viewModel.updateUiState(it) },
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateRecipe()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(16.dp)
                .verticalScroll(rememberScrollState())
        )
    }
}
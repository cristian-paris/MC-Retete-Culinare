package com.example.mc_retete_culinare.ui.recipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mc_retete_culinare.data.RecipeRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RecipeEditViewModel (
    savedStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val recipeId: Int = checkNotNull(savedStateHandle[RecipeEditDestination.recipeIdArg])

    init {
        viewModelScope.launch {
            recipeUiState = recipeRepository.getRecipeById(recipeId)
                .filterNotNull()
                .first()
                .toRecipeUiState(true)
        }
    }

    var recipeUiState by mutableStateOf(RecipeUiState())
        private set

    fun updateUiState(recipeDetails: RecipeDetails) {
        recipeUiState =
            RecipeUiState(recipeDetails = recipeDetails, isEntryValid = true)
    }

    suspend fun updateRecipe() {
        recipeRepository.updateRecipe(recipeUiState.recipeDetails.toRecipe())
    }
}
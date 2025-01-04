package com.example.mc_retete_culinare.ui.recipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mc_retete_culinare.data.RecipeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class RecipeDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val recipeId: Int = checkNotNull(savedStateHandle[RecipeDetailsDestination.recipeIdArg])
    val uiState: StateFlow<RecipeDetailsUiState> =
        recipeRepository.getRecipeById(recipeId)
            .filterNotNull()
            .map {
                RecipeDetailsUiState(recipeDetails = it.toRecipeDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = RecipeDetailsUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class RecipeDetailsUiState(
    val recipeDetails: RecipeDetails = RecipeDetails()
)


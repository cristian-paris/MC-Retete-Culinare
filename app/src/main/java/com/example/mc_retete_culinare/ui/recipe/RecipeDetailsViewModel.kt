package com.example.mc_retete_culinare.ui.recipe

import androidx.lifecycle.ViewModel



class RecipeDetailsViewModel(


) : ViewModel() {

}

data class RecipeDetailsUiState(
    val recipeDetails: RecipeDetails = RecipeDetails()
)
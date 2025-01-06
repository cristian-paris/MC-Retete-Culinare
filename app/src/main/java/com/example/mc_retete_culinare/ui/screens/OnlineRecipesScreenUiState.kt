package com.example.mc_retete_culinare.ui.screens

import com.example.mc_retete_culinare.data.Recipe

sealed interface OnlineRecipesScreenUiState {
    data class Success(
        val recipes: List<Recipe>) : OnlineRecipesScreenUiState

    data object Error : OnlineRecipesScreenUiState
    data object Loading : OnlineRecipesScreenUiState
}
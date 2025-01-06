package com.example.mc_retete_culinare.ui.recipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mc_retete_culinare.data.Recipe

class OnlineRecipeDetailsViewModel : ViewModel() {
    var currentOnlineRecipe: Recipe by mutableStateOf(Recipe())
}
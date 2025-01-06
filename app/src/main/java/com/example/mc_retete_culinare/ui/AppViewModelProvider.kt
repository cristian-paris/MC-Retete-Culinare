package com.example.mc_retete_culinare.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mc_retete_culinare.RecipesApplication
import com.example.mc_retete_culinare.ui.recipe.OfflineRecipeDetailsViewModel
import com.example.mc_retete_culinare.ui.recipe.OnlineRecipeDetailsViewModel
import com.example.mc_retete_culinare.ui.screens.OfflineRecipesViewModel
import com.example.mc_retete_culinare.ui.screens.OnlineRecipesViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Online Recipes List Screen
        initializer {
            OnlineRecipesViewModel(retrofitService = inventoryApplication().container.retrofitService)
        }

        // Online Recipe Details Screen
        initializer {
            OnlineRecipeDetailsViewModel()
        }

        initializer {
            OfflineRecipesViewModel(recipeRepository = inventoryApplication().container.recipeRepository)
        }

        // Offline Recipe Details Screen
        initializer {
            OfflineRecipeDetailsViewModel()
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [RecipesApplication].
 */
fun CreationExtras.inventoryApplication(): RecipesApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as RecipesApplication)
package com.example.mc_retete_culinare.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mc_retete_culinare.RecipesApplication
import com.example.mc_retete_culinare.ui.recipe.RecipeEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            RecipeEntryViewModel(inventoryApplication().container.recipeRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [RecipesApplication].
 */
fun CreationExtras.inventoryApplication(): RecipesApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as RecipesApplication)
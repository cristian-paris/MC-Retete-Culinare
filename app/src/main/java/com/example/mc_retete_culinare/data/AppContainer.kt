package com.example.mc_retete_culinare.data

import android.content.Context

interface AppContainer {
    val recipeRepository: RecipeRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val recipeRepository: RecipeRepository by lazy {
        RecipeRepositoryImpl(RecipeDatabase.getDatabase(context).recipeDao())
    }
}

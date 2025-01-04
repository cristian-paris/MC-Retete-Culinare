package com.example.mc_retete_culinare.data

import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipes(): Flow<List<Recipe>>
    fun getRecipeById(id: Int): Flow<Recipe?>
    suspend fun insertRecipe(recipe: Recipe)
    suspend fun updateRecipe(recipe: Recipe)
    suspend fun deleteRecipe(recipe: Recipe)
}
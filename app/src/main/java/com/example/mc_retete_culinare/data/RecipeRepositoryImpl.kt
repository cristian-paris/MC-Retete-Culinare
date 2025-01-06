package com.example.mc_retete_culinare.data

import com.example.mc_retete_culinare.network.RecipeApiService
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl(
    private val recipeDao: RecipeDao,
) : RecipeRepository {
    override fun getRecipes(): Flow<List<Recipe>> {
        return recipeDao.getRecipes()
    }

    override fun getRecipeById(id: Int): Flow<Recipe?> {
        return recipeDao.getRecipeById(id)
    }

    override suspend fun insertRecipe(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }

    override suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.updateRecipe(recipe)
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipeDao.deleteRecipe(recipe)
    }
}
package com.example.mc_retete_culinare.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes")
    fun getRecipes(): Flow<List<Recipe>>
    @Query("SELECT * FROM recipes WHERE idMeal = :id")
    fun getRecipeById(id: String): Flow<Recipe>
    @Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    fun insertRecipe(recipe: Recipe)
    @Update
    fun updateRecipe(recipe: Recipe)
    @Delete
    fun deleteRecipe(recipe: Recipe)
}
package com.example.mc_retete_culinare.ui.recipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mc_retete_culinare.data.Recipe
import com.example.mc_retete_culinare.data.RecipeRepository

class RecipeEntryViewModel (private val recipeRepository: RecipeRepository) : ViewModel() {
    var recipeUiState by mutableStateOf(RecipeUiState())
        private set

    suspend fun addRecipe() {
        recipeRepository.insertRecipe(recipeUiState.recipeDetails.toRecipe())
    }

    fun updateUiState(recipeDetails: RecipeDetails) {
        recipeUiState =
            RecipeUiState(recipeDetails = recipeDetails, isEntryValid = true)
    }
}

data class RecipeUiState(
    val recipeDetails: RecipeDetails = RecipeDetails(),
    val isEntryValid: Boolean = true
)

data class RecipeDetails(
    val idMeal: Int = 0,
    val strMeal: String = "",
    val strDrinkAlternate: String = "",
    val strCategory: String = "",
    val strArea: String = "",
    val strInstructions: String = "",
    val strMealThumb: String = "",
    val strTags: String = "",
    val strYoutube: String = "",
    val strIngredient1: String = "",
    val strIngredient2: String = "",
    val strIngredient3: String = "",
    val strIngredient4: String = "",
    val strIngredient5: String = "",
    val strIngredient6: String = "",
    val strIngredient7: String = "",
    val strIngredient8: String = "",
    val strIngredient9: String = "",
    val strIngredient10: String = "",
    val strIngredient11: String = "",
    val strIngredient12: String = "",
    val strIngredient13: String = "",
    val strIngredient14: String = "",
    val strIngredient15: String = "",
    val strIngredient16: String = "",
    val strIngredient17: String = "",
    val strIngredient18: String = "",
    val strIngredient19: String = "",
    val strIngredient20: String = "",
    val strMeasure1: String = "",
    val strMeasure2: String = "",
    val strMeasure3: String = "",
    val strMeasure4: String = "",
    val strMeasure5: String = "",
    val strMeasure6: String = "",
    val strMeasure7: String = "",
    val strMeasure8: String = "",
    val strMeasure9: String = "",
    val strMeasure10: String = "",
    val strMeasure11: String = "",
    val strMeasure12: String = "",
    val strMeasure13: String = "",
    val strMeasure14: String = "",
    val strMeasure15: String = "",
    val strMeasure16: String = "",
    val strMeasure17: String = "",
    val strMeasure18: String = "",
    val strMeasure19: String = "",
    val strMeasure20: String = "",
    val strSource: String = "",
    val strImageSource: String = "",
    val strCreativeCommonsConfirmed: String = "",
    val dateModified: String = ""
) {
    fun getIngredient(index: Int): String? {
        return when (index) {
            1 -> strIngredient1
            2 -> strIngredient2
            3 -> strIngredient3
            4 -> strIngredient4
            5 -> strIngredient5
            6 -> strIngredient6
            7 -> strIngredient7
            8 -> strIngredient8
            9 -> strIngredient9
            10 -> strIngredient10
            11 -> strIngredient11
            12 -> strIngredient12
            13 -> strIngredient13
            14 -> strIngredient14
            15 -> strIngredient15
            16 -> strIngredient16
            17 -> strIngredient17
            18 -> strIngredient18
            19 -> strIngredient19
            20 -> strIngredient20
            else -> null
        }
    }

    fun getMeasure(index: Int): String? {
        return when (index) {
            1 -> strMeasure1
            2 -> strMeasure2
            3 -> strMeasure3
            4 -> strMeasure4
            5 -> strMeasure5
            6 -> strMeasure6
            7 -> strMeasure7
            8 -> strMeasure8
            9 -> strMeasure9
            10 -> strMeasure10
            11 -> strMeasure11
            12 -> strMeasure12
            13 -> strMeasure13
            14 -> strMeasure14
            15 -> strMeasure15
            16 -> strMeasure16
            17 -> strMeasure17
            18 -> strMeasure18
            19 -> strMeasure19
            20 -> strMeasure20
            else -> null
        }
    }
}

fun RecipeDetails.toRecipe(): Recipe = Recipe(
    idMeal = idMeal,
    strMeal = strMeal,
    strDrinkAlternate = strDrinkAlternate,
    strCategory = strCategory,
    strArea = strArea,
    strInstructions = strInstructions,
    strMealThumb = strMealThumb,
    strTags = strTags,
    strYoutube = strYoutube,
    strIngredient1 = strIngredient1,
    strIngredient2 = strIngredient2,
    strIngredient3 = strIngredient3,
    strIngredient4 = strIngredient4,
    strIngredient5 = strIngredient5,
    strIngredient6 = strIngredient6,
    strIngredient7 = strIngredient7,
    strIngredient8 = strIngredient8,
    strIngredient9 = strIngredient9,
    strIngredient10 = strIngredient10,
    strIngredient11 = strIngredient11,
    strIngredient12 = strIngredient12,
    strIngredient13 = strIngredient13,
    strIngredient14 = strIngredient14,
    strIngredient15 = strIngredient15,
    strIngredient16 = strIngredient16,
    strIngredient17 = strIngredient17,
    strIngredient18 = strIngredient18,
    strIngredient19 = strIngredient19,
    strIngredient20 = strIngredient20,
)

fun Recipe.toRecipeUiState(isEntryValid: Boolean = false): RecipeUiState = RecipeUiState(
    recipeDetails = this.toRecipeDetails(),
    isEntryValid = isEntryValid
)

fun Recipe.toRecipeDetails(): RecipeDetails = RecipeDetails(
    idMeal = idMeal,
    strMeal = strMeal,
    strDrinkAlternate = strDrinkAlternate,
    strCategory = strCategory,
    strArea = strArea,
    strInstructions = strInstructions,
    strMealThumb = strMealThumb,
    strTags = strTags,
    strYoutube = strYoutube,
    strIngredient1 = strIngredient1,
    strIngredient2 = strIngredient2,
    strIngredient3 = strIngredient3,
    strIngredient4 = strIngredient4,
    strIngredient5 = strIngredient5,
    strIngredient6 = strIngredient6,
    strIngredient7 = strIngredient7,
    strIngredient8 = strIngredient8,
    strIngredient9 = strIngredient9,
    strIngredient10 = strIngredient10,
    strIngredient11 = strIngredient11,
    strIngredient12 = strIngredient12,
    strIngredient13 = strIngredient13,
    strIngredient14 = strIngredient14,
    strIngredient15 = strIngredient15,
    strIngredient16 = strIngredient16,
    strIngredient17 = strIngredient17,
    strIngredient18 = strIngredient18,
    strIngredient19 = strIngredient19,
    strIngredient20 = strIngredient20,
)

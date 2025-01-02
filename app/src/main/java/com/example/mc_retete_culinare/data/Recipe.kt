package com.example.mc_retete_culinare.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
class Recipe {
    @PrimaryKey(autoGenerate = true)
    val idMeal: String = ""
    val strMeal: String = ""
    val strDrinkAlternate: String = ""
    val strCategory: String = ""
    val strArea: String = ""
    val strInstructions: String = ""
    val strMealThumb: String = ""
    val strTags: String = ""
    val strYoutube: String = ""
    val strIngredients: List<String> = emptyList()
    val strMeasure: List<String> = emptyList()
    val strSource: String = ""
    val strImageSource: String = ""
    val strCreativeCommonsConfirmed: String = ""
    val dateModified: String = ""
}
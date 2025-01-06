package com.example.mc_retete_culinare.data

import kotlinx.serialization.Serializable

@Serializable
data class RecipeApiResponse(
    val meals: List<Recipe>
)

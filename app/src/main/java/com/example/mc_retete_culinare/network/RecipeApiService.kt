package com.example.mc_retete_culinare.network

import com.example.mc_retete_culinare.data.Recipe
import com.example.mc_retete_culinare.data.RecipeApiResponse
import retrofit2.http.GET

interface RecipeApiService {

    @GET("search.php?s=c")
    suspend fun getPhotos(): RecipeApiResponse
}
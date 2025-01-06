package com.example.mc_retete_culinare.data

import android.content.Context
import com.example.mc_retete_culinare.network.RecipeApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val recipeRepository: RecipeRepository
    val retrofitService: RecipeApiService
}

class AppDataContainer(private val context: Context) : AppContainer {
    private val baseUrl = "https://www.themealdb.com/api/json/v1/1/"


    private val json = Json {
        coerceInputValues = true // Automatically replace nulls with default values
    }


    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    override val retrofitService: RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java)
    }

    override val recipeRepository: RecipeRepository by lazy {
        RecipeRepositoryImpl(RecipeDatabase.getDatabase(context).recipeDao())
    }
}

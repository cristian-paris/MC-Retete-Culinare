package com.example.mc_retete_culinare.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.mc_retete_culinare.network.RecipeApiService
import kotlinx.coroutines.launch
import java.io.IOException

class OnlineRecipesViewModel(private val retrofitService: RecipeApiService) : ViewModel() {
    var onlineRecipesState: OnlineRecipesScreenUiState by mutableStateOf(OnlineRecipesScreenUiState.Loading)
        private set

    init {
        getOnlineRecipes()
    }

    fun getOnlineRecipes() {
        viewModelScope.launch {
            onlineRecipesState = OnlineRecipesScreenUiState.Loading
            onlineRecipesState = try {
                OnlineRecipesScreenUiState.Success(recipes = retrofitService.getPhotos().meals)
            } catch (e: IOException) {
                OnlineRecipesScreenUiState.Error
            } catch (e: HttpException) {
                OnlineRecipesScreenUiState.Error
            }
        }
    }
}
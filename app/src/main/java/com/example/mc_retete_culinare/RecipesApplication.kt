package com.example.mc_retete_culinare

import android.app.Application
import com.example.mc_retete_culinare.data.AppContainer
import com.example.mc_retete_culinare.data.AppDataContainer

class RecipesApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}

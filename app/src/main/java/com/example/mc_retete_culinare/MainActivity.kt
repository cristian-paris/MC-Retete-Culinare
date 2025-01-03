package com.example.mc_retete_culinare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mc_retete_culinare.ui.navigation.NavigationDestination
import com.example.mc_retete_culinare.ui.navigation.RecipeNavHost
import com.example.mc_retete_culinare.ui.recipe.RecipeEntryDestination
import com.example.mc_retete_culinare.ui.theme.MCReteteCulinareTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MCReteteCulinareTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RecipeNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun Greeting(
    name: String,
    navigateToItemEntry: () -> Unit,
    navigateToItemUpdate: (Int) -> Unit,
             modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToItemEntry) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Item")
            }
        }
    ) { innerPadding ->
        Text(
            text = "Hello $name!",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun RecipeApp(navController: NavHostController = rememberNavController()) {
    RecipeNavHost(navController = navController)
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MCReteteCulinareTheme {
        RecipeApp()
    }
}


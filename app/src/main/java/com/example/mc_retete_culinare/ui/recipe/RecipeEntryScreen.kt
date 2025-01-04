package com.example.mc_retete_culinare.ui.recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mc_retete_culinare.R
import com.example.mc_retete_culinare.ui.AppViewModelProvider
import com.example.mc_retete_culinare.ui.navigation.NavigationDestination
import com.example.mc_retete_culinare.ui.theme.MCReteteCulinareTheme
import kotlinx.coroutines.launch

object RecipeEntryDestination : NavigationDestination {
    override val route = "recipe_entry"
    override val titleRes = R.string.recipe_entry_title
}

@Composable
fun RecipeEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: RecipeEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    RecipeEntryBody(
        recipeUiState = viewModel.recipeUiState,
        onRecipeValueChange = viewModel::updateUiState,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.addRecipe()
                navigateBack()
            }
        },
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.padding_large),
                end = dimensionResource(id = R.dimen.padding_large),
                top = dimensionResource(id = R.dimen.padding_medium),
                bottom = dimensionResource(id = R.dimen.padding_medium)
            )
    )
}

@Composable
fun RecipeEntryBody(
    recipeUiState: RecipeUiState,
    onRecipeValueChange: (RecipeDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        RecipeInputForm(
            recipeDetails = recipeUiState.recipeDetails,
            onValueChange = onRecipeValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth(),
            enabled = true
        ) {
            Text(stringResource(R.string.save_action))
        }
    }
}

@Composable
fun RecipeInputForm(
    recipeDetails: RecipeDetails,
    modifier: Modifier = Modifier,
    onValueChange: (RecipeDetails) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = recipeDetails.strMeal,
            onValueChange = { onValueChange(recipeDetails.copy(strMeal = it)) },
            label = { Text(stringResource(R.string.recipe_name_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = recipeDetails.strCategory,
            onValueChange = { onValueChange(recipeDetails.copy(strCategory = it)) },
            label = { Text(stringResource(R.string.recipe_category_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = recipeDetails.strArea,
            onValueChange = { onValueChange(recipeDetails.copy(strArea = it)) },
            label = { Text(stringResource(R.string.recipe_category_area)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = recipeDetails.strInstructions,
            onValueChange = { onValueChange(recipeDetails.copy(strInstructions = it)) },
            label = { Text(stringResource(R.string.recipe_instructions_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = false
        )

        OutlinedTextField(
            value = recipeDetails.strMealThumb,
            onValueChange = { onValueChange(recipeDetails.copy(strMealThumb = it)) },
            label = { Text(stringResource(R.string.recipe_meal_thumb_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = false
        )

        OutlinedTextField(
            value = recipeDetails.strTags,
            onValueChange = { onValueChange(recipeDetails.copy(strTags = it)) },
            label = { Text(stringResource(R.string.recipe_tags_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = false
        )

        for (i in 1..20) {
            IngredientTextField(
                index = i,
                value = recipeDetails.getIngredient(i) ?: "",
                onValueChange = { newValue ->
                    val updatedRecipeDetails = when (i) {
                        1 -> recipeDetails.copy(strIngredient1 = newValue)
                        2 -> recipeDetails.copy(strIngredient2 = newValue)
                        3 -> recipeDetails.copy(strIngredient3 = newValue)
                        4 -> recipeDetails.copy(strIngredient4 = newValue)
                        5 -> recipeDetails.copy(strIngredient5 = newValue)
                        6 -> recipeDetails.copy(strIngredient6 = newValue)
                        7 -> recipeDetails.copy(strIngredient7 = newValue)
                        8 -> recipeDetails.copy(strIngredient8 = newValue)
                        9 -> recipeDetails.copy(strIngredient9 = newValue)
                        10 -> recipeDetails.copy(strIngredient10 = newValue)
                        11 -> recipeDetails.copy(strIngredient11 = newValue)
                        12 -> recipeDetails.copy(strIngredient12 = newValue)
                        13 -> recipeDetails.copy(strIngredient13 = newValue)
                        14 -> recipeDetails.copy(strIngredient14 = newValue)
                        15 -> recipeDetails.copy(strIngredient15 = newValue)
                        16 -> recipeDetails.copy(strIngredient16 = newValue)
                        17 -> recipeDetails.copy(strIngredient17 = newValue)
                        18 -> recipeDetails.copy(strIngredient18 = newValue)
                        19 -> recipeDetails.copy(strIngredient19 = newValue)
                        20 -> recipeDetails.copy(strIngredient20 = newValue)
                        else -> recipeDetails
                    }
                    onValueChange(updatedRecipeDetails)
                },
                enabled = enabled
            )
            MeasureTextField(
                index = i,
                value = recipeDetails.getMeasure(i) ?: "",
                onValueChange = { newValue ->
                    val updatedRecipeDetails = when (i) {
                        1 -> recipeDetails.copy(strMeasure1 = newValue)
                        2 -> recipeDetails.copy(strMeasure2 = newValue)
                        3 -> recipeDetails.copy(strMeasure3 = newValue)
                        4 -> recipeDetails.copy(strMeasure4 = newValue)
                        5 -> recipeDetails.copy(strMeasure5 = newValue)
                        6 -> recipeDetails.copy(strMeasure6 = newValue)
                        7 -> recipeDetails.copy(strMeasure7 = newValue)
                        8 -> recipeDetails.copy(strMeasure8 = newValue)
                        9 -> recipeDetails.copy(strMeasure9 = newValue)
                        10 -> recipeDetails.copy(strMeasure10 = newValue)
                        11 -> recipeDetails.copy(strMeasure11 = newValue)
                        12 -> recipeDetails.copy(strMeasure12 = newValue)
                        13 -> recipeDetails.copy(strMeasure13 = newValue)
                        14 -> recipeDetails.copy(strMeasure14 = newValue)
                        15 -> recipeDetails.copy(strMeasure15 = newValue)
                        16 -> recipeDetails.copy(strMeasure16 = newValue)
                        17 -> recipeDetails.copy(strMeasure17 = newValue)
                        18 -> recipeDetails.copy(strMeasure18 = newValue)
                        19 -> recipeDetails.copy(strMeasure19 = newValue)
                        20 -> recipeDetails.copy(strMeasure20 = newValue)
                        else -> recipeDetails
                    }
                    onValueChange(updatedRecipeDetails)
                },
                enabled = enabled
            )
        }

        Text(
            text = stringResource(R.string.required_fields),
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
        )
    }
}

@Composable
fun IngredientTextField(
    index: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Ingredient ${index}*") },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = true
    )
}

@Composable
fun MeasureTextField(
    index: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Measure ${index}*") },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = true
    )
}


@Preview
@Composable
fun PreviewRecipeEntryScreen() {
    val viewModel: RecipeEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val coroutineScope = rememberCoroutineScope()
    MCReteteCulinareTheme {
        RecipeEntryBody(
            recipeUiState = viewModel.recipeUiState,
            onRecipeValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.addRecipe()
//                    navigateBack()
                }
            },
            modifier = Modifier
        )
    }
}
package com.example.mc_retete_culinare.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mc_retete_culinare.R
import com.example.mc_retete_culinare.data.Recipe
import kotlinx.coroutines.launch

@Composable
fun RecipeIcon(
    imgSrc: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current).data(imgSrc)
            .crossfade(true).build(),
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
    )
}

// TODO RETAIN SCROLL POSITION
@Composable
fun RecipeHeaderInformation(
    recipeName: String,
    recipeArea: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = recipeName,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.str_area_format, recipeArea),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun RecipeItem(
    recipe: Recipe,
    onRecipeCardClicked: (Recipe) -> Unit,
    onSaveButtonClicked: () -> Unit,
    onDeleteButtonClicked: () -> Unit,
    isExpandedView: Boolean = false,
    showSaveButton: Boolean = false,
    showDeleteButton: Boolean = false,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = {
            onRecipeCardClicked(recipe)
        }
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                RecipeIcon(recipe.strMealThumb)
                RecipeHeaderInformation(recipe.strMeal, recipe.strArea)

            }
            if (isExpandedView) {
                RecipeDetails(
                    recipe = recipe,
                    showSaveButton = showSaveButton,
                    showDeleteButton = showDeleteButton,
                    onSaveButtonClicked = onSaveButtonClicked,
                    onDeleteButtonClicked = onDeleteButtonClicked
                )
            }
        }
    }
}

@Composable
fun RecipeDetails(
    recipe: Recipe,
    showSaveButton: Boolean,
    showDeleteButton: Boolean,
    onSaveButtonClicked: () -> Unit,
    onDeleteButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = recipe.strInstructions,
            style = MaterialTheme.typography.bodyLarge
        )

        if (showSaveButton) {
            Button(
                onClick = onSaveButtonClicked,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth(),
                enabled = true
            ) { Text(stringResource(R.string.save_action)) }
        }

        if (showDeleteButton) {
            Button(
                onClick = onDeleteButtonClicked,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth(),
                enabled = true
            ) { Text(stringResource(R.string.delete)) }
        }
    }
}

@Composable
fun RecipesList(
    recipes: List<Recipe>,
    modifier: Modifier = Modifier,
    navigate: (Recipe) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier.padding(horizontal = 4.dp)
    ) {
        items(items = recipes, key = { recipe -> recipe.idMeal }) { recipe ->
            RecipeItem(
                recipe = recipe,
                onRecipeCardClicked = {
                    navigate(recipe)
                },
                onSaveButtonClicked = {},
                onDeleteButtonClicked = {},
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}


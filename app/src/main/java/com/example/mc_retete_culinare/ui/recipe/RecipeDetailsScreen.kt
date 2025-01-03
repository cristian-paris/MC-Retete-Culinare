import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mc_retete_culinare.data.Recipe
import com.example.mc_retete_culinare.ui.theme.MCReteteCulinareTheme
import com.example.mc_retete_culinare.R

@Composable
fun DetailScreen(recipe: Recipe) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = recipe.strMeal,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = recipe.strDrinkAlternate ?: "",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            modifier = Modifier.fillMaxWidth()
        )
//        Image(
//            painter = rememberImagePainter(recipe.strMealThumb),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//                .padding(vertical = 8.dp),
//            contentScale = ContentScale.Crop
//        )
        Text(
            text = recipe.strTags ?: "",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicText(text = "Category: ${recipe.strCategory}")
        BasicText(text = "Area: ${recipe.strArea}")
        BasicText(text = "Instructions: ${recipe.strInstructions}")
        BasicText(text = "YouTube: ${recipe.strYoutube}")

        // Display ingredients followed by their measures in a table-like format
        Column {
            for (i in 1..20) {
                val ingredient = recipe.getIngredient(i)
                val measure = recipe.getMeasure(i)
                if (!ingredient.isNullOrEmpty() && !measure.isNullOrEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = ingredient,
                            modifier = Modifier
                                .weight(1f)
                                .background(Color(0xff8bb1c6))
                                .padding(8.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = measure,
                            modifier = Modifier
                                .weight(1f)
                                .background(Color(0xff8bb1c6))
                                .padding(8.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        BasicText(text = "Source: ${recipe.strSource}")
        BasicText(text = "Image Source: ${recipe.strImageSource}")
        BasicText(text = "Creative Commons Confirmed: ${recipe.strCreativeCommonsConfirmed}")
        BasicText(text = "Date Modified: ${recipe.dateModified}")
    }
}

@Composable
fun RecipeDetails (
    recipe: Recipe, modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large))
        ) {
            Text(
                text = recipe.strMeal,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
        ) {
            RecipeDetailsRow(
                labelResID = R.string.str_tags,
                itemDetail = recipe.strTags,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_medium
                    )
                )
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
        ) {
            RecipeDetailsRow(
                labelResID = R.string.str_category,
                itemDetail = recipe.strCategory,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_medium
                    )
                )
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
        ) {
            RecipeDetailsRow(
                labelResID = R.string.str_area,
                itemDetail = recipe.strArea,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_medium
                    )
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            RecipeIngredientsHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .background(Color(0xffb9d0dd))
            )
            for (i in 1..20) {
                val ingredient = recipe.getIngredient(i)
                val measure = recipe.getMeasure(i)
                if (!ingredient.isNullOrEmpty() && !measure.isNullOrEmpty()) {
                    RecipeIngredientRow(
                        ingredient = ingredient,
                        measure = measure,
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                            .background(Color(0xff8bb1c6))
                    )
                }
            }
        }

        RecipeInstructions(
            instructions = recipe.strInstructions,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        )

    }
}

@Composable
private fun RecipeDetailsRow(
    @StringRes labelResID: Int, itemDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(stringResource(labelResID))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}


@Composable
private fun RecipeIngredientsHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Ingredient",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Measure",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun RecipeIngredientRow(
    ingredient: String, measure: String, modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = modifier
                .padding(8.dp),
            text = ingredient,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = modifier
                .padding(8.dp),
            text = measure,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun RecipeInstructions(
    instructions: String, modifier: Modifier = Modifier
) {
    //Add title for instructions
    Text(
        text = "Instructions",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
    Text(
        text = instructions,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    val sampleRecipe = Recipe(
        idMeal = 1,
        strMeal = "Sample Meal",
        strDrinkAlternate = "Sample Drink",
        strCategory = "Sample Category",
        strArea = "Sample Area",
        strInstructions = "Sample Instructions",
        strMealThumb = "https://via.placeholder.com/150",
        strTags = "Sample, Tags",
        strYoutube = "https://youtube.com",
        strIngredient1 = "Ingredient1",
        strIngredient2 = "Ingredient2",
        strMeasure1 = "Measure1",
        strMeasure2 = "Measure2",
        strSource = "Sample Source",
        strImageSource = "https://via.placeholder.com/150",
        strCreativeCommonsConfirmed = "Yes",
        dateModified = "2023-01-01"
    )
    MCReteteCulinareTheme {
        RecipeDetails(recipe = sampleRecipe)
    }
}
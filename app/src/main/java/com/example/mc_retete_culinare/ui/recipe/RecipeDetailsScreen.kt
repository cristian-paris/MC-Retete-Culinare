import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mc_retete_culinare.data.Recipe
import com.example.mc_retete_culinare.ui.theme.MCReteteCulinareTheme
import com.example.mc_retete_culinare.R
import com.example.mc_retete_culinare.ui.navigation.NavigationDestination

object RecipeDetailsDestination : NavigationDestination {
    override val route = "recipe_details"
    override val titleRes = R.string.recipe_detail_title
    const val recipeIdArg = "recipeId"
    val routeWithArgs = "$route/{${recipeIdArg}"
}

@Composable
fun RecipeDetails (
    recipe: Recipe, modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
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
        MealPhotoCard(photo = recipe.strMealThumb ?: "")
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

@Composable
fun MealPhotoCard(photo: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(photo)
                .crossfade(true).build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.meal_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
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
        strMealThumb = "https://media.istockphoto.com/id/1398630614/ro/fotografie/cheeseburger-bacon-pe-un-coc-pr%C4%83jit.jpg?s=2048x2048&w=is&k=20&c=VNzMnPnwccqIFB8NFSX8dfyVVd_Son1XQuFcTUx41FE=",
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
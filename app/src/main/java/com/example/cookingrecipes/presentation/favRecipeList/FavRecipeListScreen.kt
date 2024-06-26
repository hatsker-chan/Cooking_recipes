package com.example.cookingrecipes.presentation.favRecipeList


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookingrecipes.domain.Recipe
import com.example.cookingrecipes.presentation.recipeList.RecipeCard

@Composable
fun FavRecipeListScreen(
    paddingValues: PaddingValues,
    onRecipeClickListener: (Recipe) -> Unit
) {
    val viewModel: FavouriteRecipeListViewModel = viewModel()
    val curRecipes = viewModel.favouriteRecipes.observeAsState(listOf())

    ShowRecipes(
        paddingValues = paddingValues, recipes = curRecipes.value,
        onRecipeClickListener = onRecipeClickListener
    )

//    when (val currentState = screenState.value) {
//        is RecipeListScreenState.RecipesList -> {
//            ShowRecipes(
//                paddingValues = paddingValues,
//                recipes = currentState.recipes,
//                onRecipeClickListener = onRecipeClickListener,
//            )
//        }
//
//        is RecipeListScreenState.Initial -> {}
//        RecipeListScreenState.Loading -> {
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier.fillMaxSize()
//            ) {
//                CircularProgressIndicator(
//                    modifier = Modifier.width(64.dp)
//                )
//            }
//        }
//    }
}

@Composable
private fun ShowRecipes(
    paddingValues: PaddingValues,
    recipes: List<Recipe>,
    onRecipeClickListener: (Recipe) -> Unit,
) {

    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp)
                ,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "♡ Your liked recipes ♡",
                    fontSize = 28.sp,
                    fontStyle = FontStyle.Italic,
                )
            }

        }
        items(items = recipes, key = { it.id }) { recipe ->
            RecipeCard(
                recipe = recipe,
                onRecipeClickListener = {
                    onRecipeClickListener(recipe)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
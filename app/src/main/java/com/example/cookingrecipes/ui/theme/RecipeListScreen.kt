package com.example.cookingrecipes.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cookingrecipes.domain.Recipe
import com.example.cookingrecipes.presentation.RecipeListViewModel

@Composable
fun RecipeListScreen(
    paddingValues: PaddingValues,
    onRecipeClickListener: (Recipe) -> Unit
) {
    val viewModel: RecipeListViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(RecipeListScreenState.Initial)

    when (val currentState = screenState.value) {
        is RecipeListScreenState.RecipesList -> {
            ShowRecipes(
                paddingValues = paddingValues,
                recipes = currentState.recipes,
                viewModel = viewModel,
                onRecipeClickListener = onRecipeClickListener
            )
        }

        is RecipeListScreenState.Initial -> {}
    }
}

@Composable
private fun ShowRecipes(
    paddingValues: PaddingValues,
    recipes: List<Recipe>,
    viewModel: RecipeListViewModel,
    onRecipeClickListener: (Recipe) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
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
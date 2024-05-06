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
import com.example.cookingrecipes.presentation.MainViewModel

@Composable
fun RecipeListScreen(
    paddingValues: PaddingValues,
    viewModel: MainViewModel
) {
    val recipes = viewModel.recipes.observeAsState(initial = listOf())
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        items(items = recipes.value, key = { it.id }) { recipe ->
            RecipeCard(
                recipe = recipe,
                onRecipeClickListener = {
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
package com.example.cookingrecipes.ui.theme


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cookingrecipes.presentation.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel
) {
    val recipes = viewModel.recipes.observeAsState(listOf())
    viewModel.getRecipes()
    Scaffold(
        bottomBar = {
            NavigationBar() {
                NavigationBarItem(
                    selected = false,
                    onClick = { viewModel.getRecipes() },
                    icon = {
                        Icon(Icons.Outlined.Home, contentDescription = null)
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(Icons.Outlined.FavoriteBorder, contentDescription = null)
                    }
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(items = recipes.value, key = { it.id }) {
                RecipeCard(recipe = it)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
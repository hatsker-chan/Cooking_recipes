package com.example.cookingrecipes.ui.theme


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cookingrecipes.presentation.MainViewModel
import com.example.cookingrecipes.ui.theme.NavItems.Favourite
import com.example.cookingrecipes.ui.theme.NavItems.List

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel
) {
    val recipes = viewModel.recipes.observeAsState(listOf())
    viewModel.getRecipes()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navItems = listOf(List, Favourite)
                navItems.forEach {
                    NavigationBarItem(
                        selected = false,
                        onClick = { },
                        icon = {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = it.imageVector,
                                    contentDescription = null
                                )
                                Text(text = stringResource(id = it.titleResId))
                            }
                        }
                    )
                }
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
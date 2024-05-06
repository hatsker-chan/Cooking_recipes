package com.example.cookingrecipes.ui.theme


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cookingrecipes.domain.Recipe
import com.example.cookingrecipes.presentation.MainViewModel
import com.example.cookingrecipes.presentation.navigation.AppNavGraph
import com.example.cookingrecipes.presentation.navigation.NavItems.Favourite
import com.example.cookingrecipes.presentation.navigation.NavItems.List
import com.example.cookingrecipes.presentation.navigation.rememberNavigationState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel
) {
    val navigationState = rememberNavigationState()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val navItems = listOf(List, Favourite)
                navItems.forEach {
                    NavigationBarItem(
                        selected = currentRoute == it.screen.route,
                        onClick = {
                            navigationState.navigateTo(it.screen.route)
                        },
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
        AppNavGraph(
            navHostController = navigationState.navHostController,
            recipesListContent = {
                RecipeListScreen(
                    paddingValues = paddingValues,
                    viewModel = viewModel
                )
//                RecipeInfo(
//                    paddingValues = paddingValues,
//                    recipe = viewModel.recipes.value?.get(0) ?: Recipe()
//                )
            },
            favRecipesContent = { FavouriteRecipes() })
    }
}
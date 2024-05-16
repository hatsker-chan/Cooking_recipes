package com.example.cookingrecipes.presentation.main


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
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cookingrecipes.presentation.FavouriteRecipes
import com.example.cookingrecipes.presentation.main.NavItems.Favourite
import com.example.cookingrecipes.presentation.main.NavItems.Recipes
import com.example.cookingrecipes.presentation.navigation.AppNavGraph
import com.example.cookingrecipes.presentation.navigation.rememberNavigationState
import com.example.cookingrecipes.presentation.recipeInfo.RecipeInfo
import com.example.cookingrecipes.presentation.recipeList.RecipeListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val navItems = listOf(Recipes, Favourite)
                navItems.forEach { item ->
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
                        icon = {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = item.imageVector,
                                    contentDescription = null
                                )
                                Text(text = stringResource(id = item.titleResId))
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
                    onRecipeClickListener = {
                        navigationState.navigateToRecipeInfo(it)
                    }
                )
            },
            favRecipesContent = { FavouriteRecipes() },
            recipeInfoContent = { recipe ->
                RecipeInfo(
                    paddingValues = paddingValues,
                    recipe = recipe,
                    onBackIconClicked = {
                        navigationState.navHostController.popBackStack()
                    }
                )
            }
        )
    }
}
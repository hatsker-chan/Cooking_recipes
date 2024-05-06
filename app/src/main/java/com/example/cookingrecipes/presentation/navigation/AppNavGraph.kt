package com.example.cookingrecipes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    recipesListContent: @Composable () -> Unit,
    favRecipesContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.FavRecipes.route
    ) {
        composable(Screen.RecipesList.route) {
            recipesListContent()
        }
        composable(Screen.FavRecipes.route) {
            favRecipesContent()
        }
    }
}
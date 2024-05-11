package com.example.cookingrecipes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cookingrecipes.domain.Recipe

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    recipesListContent: @Composable () -> Unit,
    favRecipesContent: @Composable () -> Unit,
    recipeInfoContent: @Composable (recipe: Recipe) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.FavRecipes.route
    ) {
        recipesTabNavGraph(
                recipesListContent = recipesListContent,
                recipeInfoContent = recipeInfoContent
            )
        composable(Screen.FavRecipes.route) {
            favRecipesContent()
        }
    }
}
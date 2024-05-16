package com.example.cookingrecipes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.cookingrecipes.domain.Recipe
import com.google.gson.Gson


fun NavGraphBuilder.recipesTabNavGraph(
    recipesListContent: @Composable () -> Unit,
    recipeInfoContent: @Composable (Recipe) -> Unit
) {
    navigation(
        startDestination = Screen.RecipesList.route,
        route = Screen.RecipesTab.route// имя вложенного графа навигации
    ) {
        composable(Screen.RecipesList.route) {
            recipesListContent()
        }

        composable(
            route = Screen.RecipeInfo.route,
            arguments = listOf(
                navArgument(Screen.KEY_RECIPE) {
                    type = NavType.StringType
                }
            )
        ) {//recipe_info/{recipe_id}
            val recipeJson = it.arguments?.getString(Screen.KEY_RECIPE) ?: ""

            val recipe = Gson().fromJson(recipeJson, Recipe::class.java)

            recipeInfoContent(recipe)
        }
    }
}
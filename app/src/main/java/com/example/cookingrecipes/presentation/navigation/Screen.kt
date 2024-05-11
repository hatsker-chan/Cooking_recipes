package com.example.cookingrecipes.presentation.navigation

import android.net.Uri
import com.example.cookingrecipes.domain.Recipe
import com.google.gson.Gson

sealed class Screen(
    val route: String
) {
    object RecipesList : Screen(
        route = ROUTE_RECIPES_LIST
    )

    object FavRecipes : Screen(
        route = ROUTE_FAV_RECIPES
    )

    object RecipesTab : Screen(route = ROUTE_RECIPES_TAB)
    object RecipeInfo : Screen(route = ROUTE_RECIPE_INFO) {

        private const val ROUTE_FOR_ARGS = "recipe_info"

        fun getRouteWithArgs(recipe: Recipe): String {
            val recipeJson = Gson().toJson(recipe)

            return "$ROUTE_FOR_ARGS/${recipeJson.encode()}"
        }
    }

    companion object {
        const val KEY_RECIPE = "recipe"

        const val ROUTE_RECIPES_TAB = "recipes_tab"
        const val ROUTE_RECIPE_INFO = "recipe_info/{$KEY_RECIPE}"
        const val ROUTE_RECIPES_LIST = "recipes_list"
        const val ROUTE_FAV_RECIPES = "fav_recipes"
    }
}


fun String.encode(): String {
    return Uri.encode(this)
}



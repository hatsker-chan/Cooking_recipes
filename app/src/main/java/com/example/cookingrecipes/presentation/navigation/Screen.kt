package com.example.cookingrecipes.presentation.navigation

sealed class Screen(
    val route: String
) {
    object RecipesList : Screen(
        route = ROUTE_RECIPES_LIST
    )

    object FavRecipes : Screen(
        route = ROUTE_FAV_RECIPES
    )

    private companion object {
        const val ROUTE_RECIPES_LIST = "recipes_list"
        const val ROUTE_FAV_RECIPES = "fav_recipes"
    }
}



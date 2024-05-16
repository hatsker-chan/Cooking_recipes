package com.example.cookingrecipes.presentation.recipeList

import com.example.cookingrecipes.domain.Recipe

sealed class RecipeListScreenState {

    object Initial : RecipeListScreenState()

    data class RecipesList(
        val recipes: List<Recipe>
    ) : RecipeListScreenState()

}
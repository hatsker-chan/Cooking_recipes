package com.example.cookingrecipes.presentation.recipeList

import com.example.cookingrecipes.domain.Recipe

sealed class RecipeListScreenState {

    object Initial : RecipeListScreenState()

    object Loading : RecipeListScreenState()

    data class RecipesList(
        val recipes: List<Recipe>,
        val nextDataIsLoading: Boolean = false
    ) : RecipeListScreenState()

}
package com.example.cookingrecipes.presentation.recipeInfo

import com.example.cookingrecipes.domain.Recipe

sealed class RecipeInfoScreenState {

    object Initial : RecipeInfoScreenState()

    data class RecipeInfo(
        val recipe: Recipe
    ) : RecipeInfoScreenState()

}
package com.example.cookingrecipes.ui.theme

import com.example.cookingrecipes.domain.Recipe

sealed class RecipeInfoScreenState {

    object Initial : RecipeInfoScreenState()

    data class RecipeInfo(
        val recipe: Recipe
    ): RecipeInfoScreenState()

}
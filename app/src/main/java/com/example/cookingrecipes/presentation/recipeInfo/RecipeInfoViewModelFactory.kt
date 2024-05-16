package com.example.cookingrecipes.presentation.recipeInfo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookingrecipes.domain.Recipe

class RecipeInfoViewModelFactory(
    private val application: Application,
    private val recipe: Recipe
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeInfoViewModel(
            application = application,
            recipe = recipe
        ) as T
    }
}
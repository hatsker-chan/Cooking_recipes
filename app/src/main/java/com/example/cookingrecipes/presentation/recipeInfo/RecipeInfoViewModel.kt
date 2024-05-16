package com.example.cookingrecipes.presentation.recipeInfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookingrecipes.domain.Recipe

class RecipeInfoViewModel(application: Application, recipe: Recipe) :
    AndroidViewModel(application) {

    private val _screenState: MutableLiveData<RecipeInfoScreenState> =
        MutableLiveData(RecipeInfoScreenState.Initial)
    val screenState: LiveData<RecipeInfoScreenState> = _screenState

    fun loadRecipeInfo(recipe: Recipe) {
        _screenState.value = RecipeInfoScreenState.RecipeInfo(recipe)
    }

    init {
        loadRecipeInfo(recipe)
    }

}
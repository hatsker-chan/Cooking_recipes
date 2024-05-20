package com.example.cookingrecipes.presentation.recipeInfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cookingrecipes.data.RecipeRepositoryImpl
import com.example.cookingrecipes.data.database.AppDatabase
import com.example.cookingrecipes.data.mapper.RecipeMapper
import com.example.cookingrecipes.data.network.ApiFactory
import com.example.cookingrecipes.domain.Recipe
import kotlinx.coroutines.launch

class RecipeInfoViewModel(application: Application, recipe: Recipe) :
    AndroidViewModel(application) {

    private val repository = RecipeRepositoryImpl(
        mapper = RecipeMapper(),
        recipeDao = AppDatabase.getInstance(application).getUserDao(),
        apiService = ApiFactory.apiService
    )

    private val _screenState: MutableLiveData<RecipeInfoScreenState> =
        MutableLiveData(RecipeInfoScreenState.Initial)
    val screenState: LiveData<RecipeInfoScreenState> = _screenState

    fun loadData(recipe: Recipe){
        viewModelScope.launch {
            val isFav = repository.getFavRecipeById(recipe.id) != null
            _screenState.value = RecipeInfoScreenState.RecipeInfo(recipe, isFav)
        }
    }

    fun changeRecipeIsFav(recipe: Recipe){
        viewModelScope.launch {
            val isFav = repository.getFavRecipeById(recipe.id) != null
            if (isFav){
                repository.removeRecipeFromFav(recipe)
            } else {
                repository.addRecipeToFav(recipe)
            }
            loadData(recipe)
        }
    }

    init {
        viewModelScope.launch{
            loadData(recipe)
        }
    }
}
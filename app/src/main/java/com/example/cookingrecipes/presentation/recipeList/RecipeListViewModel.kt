package com.example.cookingrecipes.presentation.recipeList

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cookingrecipes.data.RecipeRepositoryImpl
import com.example.cookingrecipes.data.database.AppDatabase
import com.example.cookingrecipes.data.mapper.RecipeMapper
import com.example.cookingrecipes.data.network.ApiFactory
import kotlinx.coroutines.launch

class RecipeListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RecipeRepositoryImpl(
        mapper = RecipeMapper(),
        recipeDao = AppDatabase.getInstance(application).getUserDao(),
        apiService = ApiFactory.apiService
    )

//    var _recipes: MutableLiveData<Recipes<Recipe>> = MutableLiveData(listOf(Recipe()))
//    val recipes: LiveData<Recipes<Recipe>> = _recipes


    private val initialState = RecipeListScreenState.RecipesList(listOf())

    private val _screenState: MutableLiveData<RecipeListScreenState> = MutableLiveData(initialState)
    val screenState: LiveData<RecipeListScreenState> = _screenState


    init {
        viewModelScope.launch {
            repository.loadData()
            getRecipes()
        }
    }

    fun getRecipes() {
        viewModelScope.launch {
            val recipes = repository.getRandomRecipes(10)
            _screenState.value = RecipeListScreenState.RecipesList(recipes)
        }
    }

    fun removeRecipes(){
        viewModelScope.launch {
            repository.removeData()
        }
    }
}
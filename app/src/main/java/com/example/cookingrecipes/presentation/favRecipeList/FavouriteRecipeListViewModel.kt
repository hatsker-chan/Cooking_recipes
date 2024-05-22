package com.example.cookingrecipes.presentation.favRecipeList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cookingrecipes.data.RecipeRepositoryImpl
import com.example.cookingrecipes.data.database.AppDatabase
import com.example.cookingrecipes.data.mapper.RecipeMapper
import com.example.cookingrecipes.data.network.ApiFactory
import com.example.cookingrecipes.presentation.recipeList.RecipeListScreenState

import kotlinx.coroutines.launch

class FavouriteRecipeListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RecipeRepositoryImpl(
        mapper = RecipeMapper(),
        recipeDao = AppDatabase.getInstance(application).getUserDao(),
        apiService = ApiFactory.apiService
    )

    private val initialState = RecipeListScreenState.RecipesList(listOf())

    var favouriteRecipes = repository.getFavRecipes()


//    init {
//        viewModelScope.launch {
//            repository.removeData()
//            _screenState.value = RecipeListScreenState.Loading
//            getRecipes()
//        }
//    }
//
//    private fun getRecipes() {
//        viewModelScope.launch {
//            val favRecipeList = repository.getFavRecipes()
//            _screenState.value = RecipeListScreenState.RecipesList(favRecipeList)
//        }
//    }
}
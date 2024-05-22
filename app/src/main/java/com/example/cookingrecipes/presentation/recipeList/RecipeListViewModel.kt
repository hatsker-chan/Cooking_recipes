package com.example.cookingrecipes.presentation.recipeList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cookingrecipes.data.RecipeRepositoryImpl
import com.example.cookingrecipes.data.database.AppDatabase
import com.example.cookingrecipes.data.mapper.RecipeMapper
import com.example.cookingrecipes.data.network.ApiFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RecipeListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RecipeRepositoryImpl(
        mapper = RecipeMapper(),
        recipeDao = AppDatabase.getInstance(application).getUserDao(),
        apiService = ApiFactory.apiService
    )

    private val initialState = RecipeListScreenState.RecipesList(listOf())

    private val _screenState: MutableLiveData<RecipeListScreenState> = MutableLiveData(initialState)
    val screenState: LiveData<RecipeListScreenState> = _screenState


    init {
        viewModelScope.launch {
            _screenState.value = RecipeListScreenState.Loading
            getRecipes()
        }
    }

    fun loadNextRecipes() {
        _screenState.value = RecipeListScreenState.RecipesList(
            recipes = repository.recipeList,
            nextDataIsLoading = true
        )

        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            repository.getRandomRecipes(10)
            delay(3000)
            _screenState.value = RecipeListScreenState.RecipesList(repository.recipeList)
        }
    }

    fun removeRecipes() {
        viewModelScope.launch {
            repository.removeData()
        }
    }
}
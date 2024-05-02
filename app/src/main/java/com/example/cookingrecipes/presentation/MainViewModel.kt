package com.example.cookingrecipes.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingrecipes.data.RecipeRepositoryImpl
import com.example.cookingrecipes.data.database.AppDatabase
import com.example.cookingrecipes.data.mapper.RecipeMapper
import com.example.cookingrecipes.data.network.ApiFactory
import com.example.cookingrecipes.domain.Recipe
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RecipeRepositoryImpl(
        mapper = RecipeMapper(),
        recipeDao = AppDatabase.getInstance(application).getUserDao(),
        apiService = ApiFactory.apiService
    )

    var _recipes: MutableLiveData<List<Recipe>> = MutableLiveData(listOf(Recipe()))
    val recipes : LiveData<List<Recipe>> = _recipes

    init {
        getRecipes()
    }

    fun getRecipes(){
        viewModelScope.launch {
            repository.loadData()
            val rs = repository.getRandomRecipes(1)
            _recipes.value = rs
        }
    }
}
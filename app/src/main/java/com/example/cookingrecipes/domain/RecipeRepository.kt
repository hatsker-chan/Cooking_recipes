package com.example.cookingrecipes.domain

import androidx.lifecycle.LiveData

interface RecipeRepository {

    suspend fun addRecipeToFav(recipe: Recipe)

    suspend fun getRandomRecipes(number: Int): List<Recipe>

    fun getFavRecipes(): LiveData<List<Recipe>>

    suspend fun removeData()

    suspend fun getFavRecipeById(id: Int): Recipe?

    suspend fun removeRecipeFromFav(recipe: Recipe)
}
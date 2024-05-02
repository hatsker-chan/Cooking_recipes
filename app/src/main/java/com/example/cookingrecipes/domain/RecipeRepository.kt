package com.example.cookingrecipes.domain

interface RecipeRepository {

    suspend fun getRandomRecipe(): Recipe

    fun getRandomRecipes(number: Int): List<Recipe>

    suspend fun loadData()
}
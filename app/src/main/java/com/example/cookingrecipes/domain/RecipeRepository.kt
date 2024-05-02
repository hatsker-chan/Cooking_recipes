package com.example.cookingrecipes.domain

interface RecipeRepository {

    suspend fun getRandomRecipe(): Recipe

    suspend fun getRandomRecipes(number: Int): List<Recipe>

    suspend fun loadData()
}
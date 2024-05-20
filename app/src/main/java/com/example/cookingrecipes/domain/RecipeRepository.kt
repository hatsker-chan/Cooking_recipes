package com.example.cookingrecipes.domain

interface RecipeRepository {

    suspend fun getRandomRecipe(): Recipe

    suspend fun addRecipeToFav(recipe: Recipe)

    suspend fun getRandomRecipes(number: Int): List<Recipe>

    suspend fun loadData()

    suspend fun removeData()

    suspend fun getFavRecipeById(id: Int): Recipe?

    suspend fun removeRecipeFromFav(recipe: Recipe)
}
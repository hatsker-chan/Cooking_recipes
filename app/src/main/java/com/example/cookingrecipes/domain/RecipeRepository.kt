package com.example.cookingrecipes.domain

interface RecipeRepository {

    fun getRandomRecipes(number: Int): List<Recipe>
}
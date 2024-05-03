package com.example.cookingrecipes.data

import com.example.cookingrecipes.data.database.RecipeDao
import com.example.cookingrecipes.data.mapper.RecipeMapper
import com.example.cookingrecipes.data.network.ApiService
import com.example.cookingrecipes.domain.Recipe
import com.example.cookingrecipes.domain.RecipeRepository

class RecipeRepositoryImpl(
    val mapper: RecipeMapper,
    val recipeDao: RecipeDao,
    val apiService: ApiService
) : RecipeRepository {


    override suspend fun getRandomRecipe(): Recipe {
        return recipeDao.getRecipes().map {
            mapper.mapRecipeDbModelToEntity(it)
        }[0]
    }

    override suspend fun getRandomRecipes(number: Int): List<Recipe> {
        return recipeDao.getRecipes().map {
            mapper.mapRecipeDbModelToEntity(it)
        }
    }


    override suspend fun loadData() {

        val response = apiService.loadRandomRecipe()
        response.recipes.forEach {
            val recipeDbModel = mapper.mapRecipeDtoToDbModel(it)
            recipeDao.insertRecipe(recipeDbModel)
        }
    }

    suspend fun removeData() {
        recipeDao.removeRecipes()
    }
}
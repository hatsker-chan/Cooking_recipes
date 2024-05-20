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

    override suspend fun addRecipeToFav(recipe: Recipe) {
        recipeDao.insertRecipe(mapper.mapEntityToDbModel(recipe))
    }

    override suspend fun getRandomRecipes(number: Int): List<Recipe> {
//        return recipeDao.getRecipes().map {
//            mapper.mapRecipeDbModelToEntity(it)
//        }

        return apiService.loadRandomRecipes(10).recipes.map {
            mapper.mapRecipeDbModelToEntity(mapper.mapRecipeDtoToDbModel(it))
        }
    }


    override suspend fun loadData() {
        val response = apiService.loadRandomRecipes(10)
        response.recipes.forEach {
            val recipeDbModel = mapper.mapRecipeDtoToDbModel(it)
            recipeDao.insertRecipe(recipeDbModel)
        }
    }

    override suspend fun removeData() {
        recipeDao.removeRecipes()
    }

    override suspend fun getFavRecipeById(id: Int): Recipe? {
        val favRecipe = recipeDao.getFavouriteRecipe(id)
        return if (favRecipe == null){
            null
        } else {
            mapper.mapRecipeDbModelToEntity(favRecipe)
        }
    }

    override suspend fun removeRecipeFromFav(recipe: Recipe){
        recipeDao.removeFavouriteRecipe(recipe.id)
    }
}
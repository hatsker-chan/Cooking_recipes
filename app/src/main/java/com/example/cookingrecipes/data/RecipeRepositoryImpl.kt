package com.example.cookingrecipes.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
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

    private var _recipeList = mutableSetOf<Recipe>()
    val recipeList: List<Recipe>
        get() = _recipeList.toList()

    override suspend fun addRecipeToFav(recipe: Recipe) {
        recipeDao.insertRecipe(mapper.mapEntityToDbModel(recipe))
    }

    override suspend fun getRandomRecipes(number: Int): List<Recipe> {
        val recipes = apiService.loadRandomRecipes(10).recipes.map {
            mapper.mapRecipeDbModelToEntity(mapper.mapRecipeDtoToDbModel(it))
        }
        _recipeList.addAll(recipes)
        return recipeList
    }

    override fun getFavRecipes(): LiveData<List<Recipe>> {
        val recipeDbList = recipeDao.getRecipes()
        return recipeDbList.map {list ->
            list.map {
                mapper.mapRecipeDbModelToEntity(it)
            }.reversed()
        }
    }

    override suspend fun removeData() {
        recipeDao.removeRecipes()
    }

    override suspend fun getFavRecipeById(id: Int): Recipe? {
        val favRecipe = recipeDao.getFavouriteRecipe(id)
        return if (favRecipe == null) {
            null
        } else {
            mapper.mapRecipeDbModelToEntity(favRecipe)
        }
    }

    override suspend fun removeRecipeFromFav(recipe: Recipe) {
        recipeDao.removeFavouriteRecipe(recipe.id)
    }
}
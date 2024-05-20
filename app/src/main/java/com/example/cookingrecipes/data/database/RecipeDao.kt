package com.example.cookingrecipes.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cookingrecipes.domain.Recipe

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe_info")
    suspend fun getRecipes(): List<RecipeDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeDbModel: RecipeDbModel)

    @Query("DELETE FROM recipe_info")
    suspend fun removeRecipes()

    @Query("SELECT * FROM recipe_info WHERE id =:recipeId")
    suspend fun getFavouriteRecipe(recipeId: Int): RecipeDbModel?

    @Query("DELETE FROM recipe_info WHERE id =:recipeId")
    suspend fun removeFavouriteRecipe(recipeId: Int)
}
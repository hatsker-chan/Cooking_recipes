package com.example.cookingrecipes.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe_info")
    suspend fun getRecipes(): List<RecipeDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeDbModel: RecipeDbModel)
}
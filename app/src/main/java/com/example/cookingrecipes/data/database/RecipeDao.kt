package com.example.cookingrecipes.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe_info order by timeStamps")
    fun getRecipes(): LiveData<List<RecipeDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeDbModel: RecipeDbModel)

    @Query("DELETE FROM recipe_info")
    suspend fun removeRecipes()

    @Query("SELECT * FROM recipe_info WHERE id =:recipeId")
    suspend fun getFavouriteRecipe(recipeId: Int): RecipeDbModel?

    @Query("DELETE FROM recipe_info WHERE id =:recipeId")
    suspend fun removeFavouriteRecipe(recipeId: Int)
}
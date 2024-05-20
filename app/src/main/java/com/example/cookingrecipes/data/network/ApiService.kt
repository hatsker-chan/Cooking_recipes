package com.example.cookingrecipes.data.network

import com.example.cookingrecipes.data.network.pojo.RandomRecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("random?apiKey=969381fcdaab4bf792924d419454e4c0")
    suspend fun loadRandomRecipes(@Query("number") number:Int): RandomRecipesResponse

    @GET("random?apiKey=969381fcdaab4bf792924d419454e4c0&number=5")
    suspend fun loadRandomRecipe(): RandomRecipesResponse
}
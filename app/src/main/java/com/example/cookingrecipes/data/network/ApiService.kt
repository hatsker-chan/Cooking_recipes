package com.example.cookingrecipes.data.network

import com.example.cookingrecipes.data.network.pojo.RandomRecipesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("random?apiKey=969381fcdaab4bf792924d419454e4c0&number=5")
    suspend fun loadRandomRecipes(): RandomRecipesResponse

    @GET("random?apiKey=969381fcdaab4bf792924d419454e4c0&number=1")
    suspend fun loadRandomRecipe(): RandomRecipesResponse

}
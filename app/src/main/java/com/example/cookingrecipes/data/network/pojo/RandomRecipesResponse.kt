package com.example.cookingrecipes.data.network.pojo

import com.google.gson.annotations.SerializedName

class RandomRecipesResponse {

    @SerializedName("recipes")
    var recipes: ArrayList<RecipeDto> = arrayListOf()
}
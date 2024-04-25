package com.example.cookingrecipes.data.database.typeConverters

import androidx.room.TypeConverter
import com.example.cookingrecipes.domain.Ingredient
import com.google.gson.Gson

class IngredientConverter {

    @TypeConverter
    fun fromIngredients(ingredients: List<Ingredient>): String {
        return Gson().toJson(ingredients)
    }

    @TypeConverter
    fun toIngredients(jsonIngredient: String): List<Ingredient> {
        return Gson().fromJson(jsonIngredient, Array<Ingredient>::class.java).toList()
    }
}
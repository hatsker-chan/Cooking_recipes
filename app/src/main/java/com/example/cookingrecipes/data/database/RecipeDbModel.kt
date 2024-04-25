package com.example.cookingrecipes.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.cookingrecipes.data.database.typeConverters.IngredientConverter
import com.example.cookingrecipes.domain.Ingredient
import com.example.cookingrecipes.domain.InstructionStep

@Entity(tableName = "recipe_info")
@TypeConverters(IngredientConverter::class, InstructionStep::class)
data class RecipeDbModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val ingredients: List<Ingredient>,
    val steps: List<InstructionStep>,
    val summaryDescription: String,
    val sourceUrl: String,
    val imageUrl: String
)

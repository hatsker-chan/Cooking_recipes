package com.example.cookingrecipes.domain


data class Recipe (
    val id: Int = -1,
    val name: String = "",
    val ingredients: List<Ingredient> = listOf<Ingredient>(),
    val steps: List<InstructionStep> = listOf<InstructionStep>(),
    val summaryDescription: String = "",
    val sourceUrl: String = "",
    val imageUrl: String = ""
)
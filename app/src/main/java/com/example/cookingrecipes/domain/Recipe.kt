package com.example.cookingrecipes.domain


data class Recipe(
    val id: Int = -1,
    val name: String = "",
    val ingredients: List<Ingredient> = listOf(),
    val steps: List<InstructionStep> = listOf(),
    val summaryDescription: String = "",
    val sourceUrl: String = "",
    val imageUrl: String = ""
)
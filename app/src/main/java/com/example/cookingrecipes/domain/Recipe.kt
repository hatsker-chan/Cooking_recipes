package com.example.cookingrecipes.domain

import androidx.compose.ui.graphics.ImageBitmap


data class Recipe(
    val id: Int = -1,
    val name: String = "",
    val ingredients: List<Ingredient> = listOf<Ingredient>(),
    val steps: List<InstructionStep> = listOf<InstructionStep>(),
    val imageBitmap: ImageBitmap = ImageBitmap(1, 1),
    val summaryDescription: String = "",
    val sourceUrl: String = "",
    val imageUrl: String = ""
)
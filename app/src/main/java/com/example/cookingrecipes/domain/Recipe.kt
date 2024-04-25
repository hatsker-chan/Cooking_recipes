package com.example.cookingrecipes.domain


class Recipe (
    val id: Int,
    val name: String,
    val ingredients: List<Ingredient>,
    val steps: List<InstructionStep>,
    val summaryDescription: String,
    val sourceUrl: String,
    val imageUrl: String
)
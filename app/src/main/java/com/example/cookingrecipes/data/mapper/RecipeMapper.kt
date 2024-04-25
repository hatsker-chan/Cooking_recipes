package com.example.cookingrecipes.data.mapper

import com.example.cookingrecipes.data.database.RecipeDbModel
import com.example.cookingrecipes.data.network.pojo.IngredientDto
import com.example.cookingrecipes.data.network.pojo.InstructionStepsJsonContainer
import com.example.cookingrecipes.data.network.pojo.MeasuresJsonContainer
import com.example.cookingrecipes.data.network.pojo.RecipeDto
import com.example.cookingrecipes.domain.Ingredient
import com.example.cookingrecipes.domain.InstructionStep

class RecipeMapper {

    fun mapRecipeDtoToDbModel(recipeDto: RecipeDto): RecipeDbModel {
        return RecipeDbModel(
            id = recipeDto.id,
            name = recipeDto.title ?: "",
            ingredients = recipeDto.extendedIngredients.map {
                mapIngredientDtoToModel(it)
            },
            steps = mapStepsJsonContainerToStepsList(recipeDto.analyzedInstructions[0]),
            summaryDescription = recipeDto.summary ?: "",
            sourceUrl = recipeDto.sourceUrl ?: "",
            imageUrl = recipeDto.image ?: ""
            )
    }

    fun mapIngredientDtoToModel(ingredientDto: IngredientDto): Ingredient {
        return Ingredient(
            id = ingredientDto.id,
            name = ingredientDto.name ?: "",
            amount = ingredientDto.amount ?: 0,
            measure = mapMetricJsonContainerToMeasure(ingredientDto.metricJsonContainer)
        )
    }


    fun mapMetricJsonContainerToMeasure(measuresJsonContainer: MeasuresJsonContainer?): String {
        measuresJsonContainer?.let {
            val metricDto = measuresJsonContainer.metric
            return "${metricDto?.amount} ${metricDto?.unitShort}"
        }
        return ""
    }

    fun mapStepsJsonContainerToStepsList(instructionStepsJsonContainer: InstructionStepsJsonContainer?): List<InstructionStep>{

        instructionStepsJsonContainer?.let {
            val stepsDto = instructionStepsJsonContainer.steps
            val stepsList = stepsDto.map {
                InstructionStep(
                    number = it.number ?: 0,
                    description = it.step ?: ""
                )
            }
            return stepsList
        }
        return listOf()
    }

}
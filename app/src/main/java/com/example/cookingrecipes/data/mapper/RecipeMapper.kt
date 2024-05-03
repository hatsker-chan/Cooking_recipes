package com.example.cookingrecipes.data.mapper

import android.net.Uri
import androidx.compose.ui.graphics.asImageBitmap
import com.example.cookingrecipes.data.database.RecipeDbModel
import com.example.cookingrecipes.data.network.pojo.IngredientDto
import com.example.cookingrecipes.data.network.pojo.InstructionStepsJsonContainer
import com.example.cookingrecipes.data.network.pojo.MeasuresJsonContainer
import com.example.cookingrecipes.data.network.pojo.RecipeDto
import com.example.cookingrecipes.domain.Ingredient
import com.example.cookingrecipes.domain.InstructionStep
import com.example.cookingrecipes.domain.Recipe
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class RecipeMapper {

    suspend fun mapRecipeDbModelToEntity(recipeDbModel: RecipeDbModel): Recipe {
        return CoroutineScope(Dispatchers.IO).async {
            Recipe(
                id = recipeDbModel.id,
                name = recipeDbModel.name,
                ingredients = recipeDbModel.ingredients,
                steps = recipeDbModel.steps,
                summaryDescription = recipeDbModel.summaryDescription,
                sourceUrl = recipeDbModel.sourceUrl,
                imageUrl = recipeDbModel.imageUrl,
                imageBitmap = Picasso.get().load(recipeDbModel.imageUrl).get().asImageBitmap()
            )
        }.await()
    }

    fun mapRecipeDtoToDbModel(recipeDto: RecipeDto): RecipeDbModel {
        return RecipeDbModel(
            id = recipeDto.id ?: 0,
            name = recipeDto.title ?: "",
            ingredients = recipeDto.extendedIngredients.map {
                mapIngredientDtoToModel(it)
            },
            steps = mapStepsJsonContainerToStepsList(recipeDto.analyzedInstructions[0]),
            summaryDescription = recipeDto.summary ?: "",
            sourceUrl = recipeDto.sourceUrl ?: "",
            imageUrl = recipeDto.image ?: "https://yoomag.ru/image/cache/no_image-500x500.png"
        )
    }

    fun mapIngredientDtoToModel(ingredientDto: IngredientDto): Ingredient {
        return Ingredient(
            id = ingredientDto.id,
            name = titleText(ingredientDto.name ?: ""),
            amount = ingredientDto.amount ?: 0f,
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

    fun mapStepsJsonContainerToStepsList(instructionStepsJsonContainer: InstructionStepsJsonContainer?): List<InstructionStep> {

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

    fun titleText(string: String): String {
        return string[0].uppercase() + string.substring(1)
    }

}
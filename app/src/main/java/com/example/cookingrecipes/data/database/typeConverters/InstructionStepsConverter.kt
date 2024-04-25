package com.example.cookingrecipes.data.database.typeConverters

import androidx.room.TypeConverter
import com.example.cookingrecipes.domain.Ingredient
import com.example.cookingrecipes.domain.InstructionStep
import com.google.gson.Gson

class InstructionStepsConverter {

    @TypeConverter
    fun fromInstructionSteps(instructionSteps: List<InstructionStep>): String {
        return Gson().toJson(instructionSteps)
    }

    @TypeConverter
    fun toInstructionSteps(jsonInstructionSteps: String): List<InstructionStep> {
        return Gson().fromJson(jsonInstructionSteps, Array<InstructionStep>::class.java).toList()
    }
}
package com.example.cookingrecipes.data.network.pojo

import com.google.gson.annotations.SerializedName

data class InstructionStepsJsonContainer(

    @SerializedName("name") var name: String? = null,
    @SerializedName("steps") var steps: ArrayList<StepDto> = arrayListOf()

)
package com.example.cookingrecipes.data.network.pojo

import com.google.gson.annotations.SerializedName

data class StepDto(

    @SerializedName("number") var number: Int? = null,
    @SerializedName("step") var step: String? = null
)

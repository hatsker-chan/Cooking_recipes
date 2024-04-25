package com.example.cookingrecipes.data.network.pojo

import com.google.gson.annotations.SerializedName

data class MetricDto (

    @SerializedName("amount"    ) var amount    : Double? = null,
    @SerializedName("unitShort" ) var unitShort : String? = null,
    @SerializedName("unitLong"  ) var unitLong  : String? = null

)
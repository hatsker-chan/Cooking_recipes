package com.example.cookingrecipes.data.network.pojo

import com.google.gson.annotations.SerializedName

data class MeasuresJsonContainer (

    //@SerializedName("us"     ) var us     : Us?     = Us(),
    @SerializedName("metric" ) var metric : MetricDto? = MetricDto()

)
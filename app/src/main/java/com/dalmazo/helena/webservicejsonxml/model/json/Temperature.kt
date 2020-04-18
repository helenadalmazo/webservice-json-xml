package com.dalmazo.helena.webservicejsonxml.model.json

import com.google.gson.annotations.SerializedName

class Temperature(
    val name: String,
    val symbol: String,
    @SerializedName("freezing_point_of_water") val freezingPointOfWater: Int,
    @SerializedName("boiling_point_of_water") val boilingPointOfWater: Int
)
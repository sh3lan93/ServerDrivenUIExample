package com.example.server_drivenuiexample.ui.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MainScreenComponents(
    @Json(name = "components")
    val components: List<Component> = emptyList()
)
package com.example.server_drivenuiexample.ui.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Content(
    @Json(name = "text")
    val text: String?,
    @Json(name = "title")
    val title: String?
)
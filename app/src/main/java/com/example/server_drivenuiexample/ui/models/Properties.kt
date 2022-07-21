package com.example.server_drivenuiexample.ui.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Properties(
    @Json(name = "color")
    val color: String?,
    @Json(name = "height")
    val height: Int?,
    @Json(name = "margin_end")
    val marginEnd: Int?,
    @Json(name = "margin_start")
    val marginStart: Int?,
    @Json(name = "margin_top")
    val marginTop: Int?,
    @Json(name = "margin_bottom")
    val marginBottom: Int?,
    @Json(name = "text_color")
    val textColor: String?,
    @Json(name = "ratio")
    val ratio: String?,
    @Json(name = "opacity")
    val opacity: Float?
)
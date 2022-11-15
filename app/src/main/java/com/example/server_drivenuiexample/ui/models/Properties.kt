package com.example.server_drivenuiexample.ui.models


import com.example.server_drivenuiexample.ui.design_system_language.ScaleType
import com.example.server_drivenuiexample.ui.utils.mapToScaleType
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
    val opacity: Float?,
    @Json(name = "padding")
    val padding: Int?,
    @Json(name = "corner_radius")
    val cornerRadius: Int?,
    @Json(name = "scale_type")
    val scaleType: String?
) {
    val imageScaleType: ScaleType
        get() = scaleType?.mapToScaleType() ?: ScaleType.DEFAULT
}
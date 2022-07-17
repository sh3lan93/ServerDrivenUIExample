package com.example.server_drivenuiexample.ui.models


import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.utils.mapToClickType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OnClick(
    @Json(name = "case")
    val case: String?,
    @Json(name = "data")
    val `data`: Data?
) {
    val clickType: ActionTypes
        get() = case?.mapToClickType() ?: ActionTypes.UNKNOWN
}
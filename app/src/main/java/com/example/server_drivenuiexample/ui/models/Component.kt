package com.example.server_drivenuiexample.ui.models


import com.example.server_drivenuiexample.ui.design_system_language.ComponentsType
import com.example.server_drivenuiexample.ui.utils.mapToComponentType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Component(
    @Json(name = "actions")
    val actions: Actions?,
    @Json(name = "content")
    val content: Content?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "properties")
    val properties: Properties?,
    @Json(name = "type")
    val type: String?
) {

    val componentType: ComponentsType
        get() = type?.mapToComponentType() ?: ComponentsType.UNKNOWN
}
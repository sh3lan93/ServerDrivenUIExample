package com.example.server_drivenuiexample.ui.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Content(
    @Json(name = "text")
    val text: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "component")
    val component: Component?,
    @Json(name = "icon")
    val icon: String?,
    @Json(name = "items")
    val items: List<Component>?,
    @Json(name = "properties")
    val properties: Properties?,
    @Json(name = "stack")
    val stack: List<Component>?
)
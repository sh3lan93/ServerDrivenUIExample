package com.example.server_drivenuiexample.network.requests

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddToCartRequest(@Json(name = "sku") val sku: String)

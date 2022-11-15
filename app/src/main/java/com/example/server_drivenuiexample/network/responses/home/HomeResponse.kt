package com.example.server_drivenuiexample.network.responses.home

import com.example.server_drivenuiexample.ui.models.Component
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeResponse(val components: List<Component>)

package com.example.server_drivenuiexample.ui.models


import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.design_system_language.NetworkRequests
import com.example.server_drivenuiexample.ui.utils.mapToClickType
import com.example.server_drivenuiexample.ui.utils.mapToNetworkRequest
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OnClick(
    @Json(name = "case")
    val case: String?,
    @Json(name = "data")
    val `data`: Data?,
    @Json(name = "request")
    val request: String?
) {

    val clickType: ActionTypes
        get() = case?.mapToClickType() ?: ActionTypes.UNKNOWN

    val requestType: NetworkRequests
        get() = request?.mapToNetworkRequest() ?: NetworkRequests.NON
}
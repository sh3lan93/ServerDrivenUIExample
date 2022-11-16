package com.example.server_drivenuiexample.ui.utils

import com.example.server_drivenuiexample.ui.design_system_language.*
import java.util.*


fun String.mapToComponentType(): ComponentsType = ComponentsType.valueOf(this)

fun String.mapToClickType(): ActionTypes = ActionTypes.valueOf(this)

fun String.mapToStyle(): Style = Style.valueOf(this)

fun String.mapToScaleType(): ScaleType = ScaleType.valueOf(this.uppercase(Locale.ENGLISH))

fun String.mapToNetworkRequest(): NetworkRequests =
    NetworkRequests.valueOf(this.uppercase(Locale.ENGLISH))
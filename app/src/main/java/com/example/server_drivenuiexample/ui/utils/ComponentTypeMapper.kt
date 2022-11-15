package com.example.server_drivenuiexample.ui.utils

import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.design_system_language.ComponentsType
import com.example.server_drivenuiexample.ui.design_system_language.ScaleType
import com.example.server_drivenuiexample.ui.design_system_language.Style
import java.util.*


fun String.mapToComponentType(): ComponentsType = ComponentsType.valueOf(this)

fun String.mapToClickType(): ActionTypes = ActionTypes.valueOf(this)

fun String.mapToStyle(): Style = Style.valueOf(this)

fun String.mapToScaleType(): ScaleType = ScaleType.valueOf(this.uppercase(Locale.ENGLISH))
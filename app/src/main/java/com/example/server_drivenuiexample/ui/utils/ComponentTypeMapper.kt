package com.example.server_drivenuiexample.ui.utils

import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.design_system_language.ComponentsType


fun String.mapToComponentType(): ComponentsType = ComponentsType.valueOf(this)

fun String.mapToClickType(): ActionTypes = ActionTypes.valueOf(this)
package com.example.server_drivenuiexample.utils


inline fun <reified Model> String.toModel(): Model? = JsonMapperManager.mapFromJson(this)
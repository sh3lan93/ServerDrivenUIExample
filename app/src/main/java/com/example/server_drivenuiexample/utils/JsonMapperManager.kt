package com.example.server_drivenuiexample.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object JsonMapperManager {

    val moshi: Moshi by lazy {
        initMoshi()
    }

    private fun initMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    inline fun <reified Model> mapFromJson(json: String): Model? =
        moshi.adapter(Model::class.java).fromJson(json)
}
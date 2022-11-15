package com.example.server_drivenuiexample.network.services

import com.example.server_drivenuiexample.network.responses.home.HomeResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface AppServices {

    @GET("home")
    fun getHomeContent(): Single<HomeResponse>
}
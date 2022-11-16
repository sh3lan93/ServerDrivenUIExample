package com.example.server_drivenuiexample.network.services

import com.example.server_drivenuiexample.network.requests.AddToCartRequest
import com.example.server_drivenuiexample.network.responses.home.UIResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AppServices {

    @GET("home")
    fun getHomeContent(): Single<UIResponse>

    @GET("offers")
    fun getOffersContent(): Single<UIResponse>

    @GET("products/{sku}")
    fun getProduct(@Path("sku") sku: String): Single<UIResponse>

    @POST("add_product_to_cart")
    fun addProductToCart(@Body requestBody: AddToCartRequest): Completable
}
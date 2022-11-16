package com.example.server_drivenuiexample.usecases

import com.example.server_drivenuiexample.base.usecase.BaseSingleUseCaseWithParam
import com.example.server_drivenuiexample.base.usecase.Param
import com.example.server_drivenuiexample.network.responses.home.UIResponse
import com.example.server_drivenuiexample.network.services.AppServices
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

class ProductContentUseCase(
    private val service: AppServices,
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler
) :
    BaseSingleUseCaseWithParam<UIResponse, ProductContentUseCase.ProductParams>() {


    data class ProductParams(val productSku: String) : Param

    override fun buildUseCase(param: ProductParams): Single<UIResponse> =
        service.getProduct(sku = param.productSku)
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
}
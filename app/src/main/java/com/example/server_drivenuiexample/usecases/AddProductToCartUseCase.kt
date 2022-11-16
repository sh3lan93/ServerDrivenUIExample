package com.example.server_drivenuiexample.usecases

import com.example.server_drivenuiexample.base.usecase.BaseCompletableUseCaseWithParam
import com.example.server_drivenuiexample.base.usecase.Param
import com.example.server_drivenuiexample.network.requests.AddToCartRequest
import com.example.server_drivenuiexample.network.services.AppServices
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler

class AddProductToCartUseCase(
    private val service: AppServices,
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler
) : BaseCompletableUseCaseWithParam<AddProductToCartUseCase.AddProductUseCaseParam>() {

    data class AddProductUseCaseParam(val sku: String) : Param

    override fun buildUseCase(param: AddProductUseCaseParam): Completable =
        service.addProductToCart(requestBody = AddToCartRequest(param.sku))
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
}
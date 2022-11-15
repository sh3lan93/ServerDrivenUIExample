package com.example.server_drivenuiexample.usecases

import com.example.server_drivenuiexample.base.usecase.BaseSingleUseCase
import com.example.server_drivenuiexample.network.responses.home.UIResponse
import com.example.server_drivenuiexample.network.services.AppServices
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

class OffersContentUseCase(
    private val service: AppServices,
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler
) : BaseSingleUseCase<UIResponse>() {


    override fun buildUseCase(): Single<UIResponse> = service.getOffersContent()
        .subscribeOn(subscribeOnScheduler)
        .observeOn(observeOnScheduler)
}
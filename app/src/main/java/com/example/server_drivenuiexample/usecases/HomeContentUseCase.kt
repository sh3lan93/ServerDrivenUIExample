package com.example.server_drivenuiexample.usecases

import com.example.server_drivenuiexample.base.usecase.BaseSingleUseCase
import com.example.server_drivenuiexample.network.responses.home.HomeResponse
import com.example.server_drivenuiexample.network.services.AppServices
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

class HomeContentUseCase(
    private val service: AppServices,
    private val subscribeOnScheduler: Scheduler,
    private val observeOnScheduler: Scheduler
) : BaseSingleUseCase<HomeResponse>() {

    override fun buildUseCase(): Single<HomeResponse> =
        service.getHomeContent()
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
}
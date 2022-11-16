package com.example.server_drivenuiexample.base.usecase

import io.reactivex.rxjava3.core.Single

abstract class BaseSingleUseCaseWithParam<T : Any, Params : Param> {

    abstract fun buildUseCase(param: Params): Single<T>
}
package com.example.server_drivenuiexample.base.usecase

import io.reactivex.rxjava3.core.Completable

abstract class BaseCompletableUseCaseWithParam<Params: Param> {

    abstract fun buildUseCase(param: Params): Completable
}
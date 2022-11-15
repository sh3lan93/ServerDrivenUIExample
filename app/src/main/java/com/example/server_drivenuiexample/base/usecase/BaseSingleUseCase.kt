package com.example.server_drivenuiexample.base.usecase

import io.reactivex.rxjava3.core.Single

abstract class BaseSingleUseCase<T : Any> {

    abstract fun buildUseCase(): Single<T>
}
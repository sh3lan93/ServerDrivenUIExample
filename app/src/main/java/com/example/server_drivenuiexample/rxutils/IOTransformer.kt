package com.example.server_drivenuiexample.rxutils

import com.example.server_drivenuiexample.di.IO_THREAD
import com.example.server_drivenuiexample.di.MAIN_THREAD
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleSource
import io.reactivex.rxjava3.core.SingleTransformer
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class IOTransformer<Source>() : SingleTransformer<Source, Source>, KoinComponent {

    private val ioScheduler: Scheduler by inject(named(IO_THREAD))
    private val androidMainThreadScheduler: Scheduler by inject(named(MAIN_THREAD))

    override fun apply(upstream: Single<Source>): SingleSource<Source> = upstream
        .subscribeOn(ioScheduler)
        .observeOn(androidMainThreadScheduler)
}
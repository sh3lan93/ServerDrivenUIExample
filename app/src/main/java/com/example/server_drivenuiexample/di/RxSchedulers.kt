package com.example.server_drivenuiexample.di

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.qualifier.named
import org.koin.dsl.module


const val MAIN_THREAD = "android_main_thread"
const val IO_THREAD = "io_thread"

val rxSchedulersModule = module {
    single<Scheduler>(named(MAIN_THREAD)) { AndroidSchedulers.mainThread() }

    single<Scheduler>(named(IO_THREAD)) { Schedulers.io() }
}
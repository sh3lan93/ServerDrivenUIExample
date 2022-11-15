package com.example.server_drivenuiexample.di

import com.example.server_drivenuiexample.usecases.HomeContentUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module


val useCaseModule = module {

    factory {
        HomeContentUseCase(
            service = get(), subscribeOnScheduler = get(named(IO_THREAD)), observeOnScheduler = get(
                named(MAIN_THREAD)
            )
        )
    }
}
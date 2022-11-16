package com.example.server_drivenuiexample.di

import com.example.server_drivenuiexample.usecases.AddProductToCartUseCase
import com.example.server_drivenuiexample.usecases.HomeContentUseCase
import com.example.server_drivenuiexample.usecases.OffersContentUseCase
import com.example.server_drivenuiexample.usecases.ProductContentUseCase
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

    factory {
        OffersContentUseCase(
            service = get(), subscribeOnScheduler = get(named(IO_THREAD)), observeOnScheduler = get(
                named(MAIN_THREAD)
            )
        )
    }

    factory {
        ProductContentUseCase(
            service = get(), subscribeOnScheduler = get(named(IO_THREAD)), observeOnScheduler = get(
                named(MAIN_THREAD)
            )
        )
    }

    factory {
        AddProductToCartUseCase(
            service = get(), subscribeOnScheduler = get(named(IO_THREAD)), observeOnScheduler = get(
                named(MAIN_THREAD)
            )
        )
    }
}
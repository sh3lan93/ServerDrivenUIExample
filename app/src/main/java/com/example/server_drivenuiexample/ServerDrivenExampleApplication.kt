package com.example.server_drivenuiexample

import android.app.Application
import com.example.server_drivenuiexample.di.networkingModules
import com.example.server_drivenuiexample.di.rxSchedulersModule
import com.example.server_drivenuiexample.di.useCaseModule
import com.example.server_drivenuiexample.di.viewModelsModule
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

class ServerDrivenExampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            logger(AndroidLogger())
            modules(rxSchedulersModule, viewModelsModule, networkingModules, useCaseModule)
        }
    }
}
package com.example.server_drivenuiexample.di

import com.example.server_drivenuiexample.ui.complex_content.ComplexContentViewModel
import com.example.server_drivenuiexample.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val viewModelsModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::ComplexContentViewModel)
}
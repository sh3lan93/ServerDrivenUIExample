package com.example.server_drivenuiexample.di

import com.example.server_drivenuiexample.ui.home.HomeViewModel
import com.example.server_drivenuiexample.ui.main.MainViewModel
import com.example.server_drivenuiexample.ui.offers.OffersViewModel
import com.example.server_drivenuiexample.ui.product.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val viewModelsModule = module {
    viewModelOf(::MainViewModel)
    viewModel { HomeViewModel(homeContentUseCase = get()) }
    viewModel { OffersViewModel(offersUseCase = get()) }
    viewModel { ProductViewModel(productUseCase = get(), addToCartUseCase = get()) }
}
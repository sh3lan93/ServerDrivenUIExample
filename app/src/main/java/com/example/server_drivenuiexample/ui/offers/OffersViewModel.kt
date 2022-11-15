package com.example.server_drivenuiexample.ui.offers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.server_drivenuiexample.base.BaseViewModel
import com.example.server_drivenuiexample.states.Result
import com.example.server_drivenuiexample.ui.models.Component
import com.example.server_drivenuiexample.usecases.OffersContentUseCase

class OffersViewModel(private val offersUseCase: OffersContentUseCase) : BaseViewModel() {

    private val _offersResult: MutableLiveData<Result<List<Component>>> by lazy {
        MutableLiveData()
    }

    val offersResult: LiveData<Result<List<Component>>> =
        _offersResult

    fun getOffers() {
        offersUseCase.buildUseCase()
            .map { it.components }
            .async(_offersResult)
    }
}
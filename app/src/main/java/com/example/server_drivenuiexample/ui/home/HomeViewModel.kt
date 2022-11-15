package com.example.server_drivenuiexample.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.server_drivenuiexample.base.BaseViewModel
import com.example.server_drivenuiexample.states.Result
import com.example.server_drivenuiexample.ui.models.Component
import com.example.server_drivenuiexample.usecases.HomeContentUseCase

class HomeViewModel(private val homeContentUseCase: HomeContentUseCase) : BaseViewModel() {

    private val _homeContentResult: MutableLiveData<Result<List<Component>>> by lazy {
        MutableLiveData()
    }

    val homeContentResult: LiveData<Result<List<Component>>> =
        _homeContentResult


    fun getHomeContent(isRefreshRequest: Boolean = false) {
        when {
            _homeContentResult.value == null && isRefreshRequest.not() -> getHomeContent()
            _homeContentResult.value != null && isRefreshRequest -> getHomeContent()
        }
    }

    private fun getHomeContent() {
        homeContentUseCase.buildUseCase()
            .map { it.components }
            .async(_homeContentResult)
    }
}
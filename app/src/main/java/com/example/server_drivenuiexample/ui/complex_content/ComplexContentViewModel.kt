package com.example.server_drivenuiexample.ui.complex_content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.server_drivenuiexample.base.BaseViewModel
import com.example.server_drivenuiexample.states.Result
import com.example.server_drivenuiexample.ui.models.Component
import com.example.server_drivenuiexample.ui.models.MainScreenComponents
import com.example.server_drivenuiexample.utils.toModel
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class ComplexContentViewModel : BaseViewModel() {

    private val screenContentResult: MutableLiveData<Result<List<Component>>> by lazy {
        MutableLiveData()
    }

    val screenContentResult_: LiveData<Result<List<Component>>> = screenContentResult

    fun mapJsonToScreenContent(json: String) {
        Single.just(json)
            .delay(5, TimeUnit.SECONDS)
            .map {
                it.toModel<MainScreenComponents>() ?: MainScreenComponents()
            }.map {
                it.components
            }.async(screenContentResult)
    }
}
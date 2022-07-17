package com.example.server_drivenuiexample.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.server_drivenuiexample.rxutils.IOTransformer
import com.example.server_drivenuiexample.rxutils.mapToError
import com.example.server_drivenuiexample.states.Result
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo

open class BaseViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun <Data> Single<Data>.async(livedata: MutableLiveData<Result<Data>>) {
        livedata.value = Result.loading()
        this.compose(IOTransformer())
            .subscribe({ data ->
                livedata.value = Result.success(data = data)
            }) { throwable ->
                livedata.value = Result.error(throwable.mapToError())
            }.addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }

}
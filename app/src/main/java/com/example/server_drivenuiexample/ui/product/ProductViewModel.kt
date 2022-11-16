package com.example.server_drivenuiexample.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.server_drivenuiexample.base.BaseViewModel
import com.example.server_drivenuiexample.states.Result
import com.example.server_drivenuiexample.ui.design_system_language.NetworkRequests
import com.example.server_drivenuiexample.ui.models.Component
import com.example.server_drivenuiexample.ui.utils.mapToNetworkRequest
import com.example.server_drivenuiexample.usecases.AddProductToCartUseCase
import com.example.server_drivenuiexample.usecases.ProductContentUseCase

class ProductViewModel(
    private val productUseCase: ProductContentUseCase,
    private val addToCartUseCase: AddProductToCartUseCase
) : BaseViewModel() {

    private val _productResult: MutableLiveData<Result<List<Component>>> by lazy {
        MutableLiveData()
    }

    val productResult: LiveData<Result<List<Component>>> = _productResult

    private val _addToCartResult: MutableLiveData<Result<Any>> by lazy {
        MutableLiveData()
    }

    val addToCartResult: LiveData<Result<Any>> = _addToCartResult

    fun getProduct(sku: String) {
        productUseCase.buildUseCase(param = ProductContentUseCase.ProductParams(productSku = sku))
            .map { it.components }
            .async(_productResult)
    }

    fun handleNetworkRequest(networkRequest: String, sku: String) {
        when (val request = networkRequest.mapToNetworkRequest()) {
            NetworkRequests.ADD_TO_CART -> addToCartUseCase.buildUseCase(
                param = AddProductToCartUseCase.AddProductUseCaseParam(
                    sku = sku
                )
            ).async(_addToCartResult)
            NetworkRequests.NON -> TODO()
        }
    }
}
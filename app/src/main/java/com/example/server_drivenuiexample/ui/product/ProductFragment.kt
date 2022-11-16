package com.example.server_drivenuiexample.ui.product

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.server_drivenuiexample.R
import com.example.server_drivenuiexample.base.BaseFragment
import com.example.server_drivenuiexample.databinding.FragmentProductBinding
import com.example.server_drivenuiexample.states.ScreenState
import com.example.server_drivenuiexample.ui.controller.MainController
import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.utils.ComponentClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment :
    BaseFragment<FragmentProductBinding, ProductViewModel>(layoutId = R.layout.fragment_product) {

    private val args: ProductFragmentArgs by navArgs()

    override val viewmodel: ProductViewModel by viewModel()

    private val controller: MainController by lazy {
        MainController(buttonClickListener = clickListener)
    }

    private val clickListener: ComponentClickListener = { type, uri ->
        when (type) {
            ActionTypes.DEEP_LINK -> findNavController().navigate(Uri.parse(uri))
            ActionTypes.DISMISS -> TODO()
            ActionTypes.ASYNC_REQUEST -> viewmodel.handleNetworkRequest(uri, args.sku)
            ActionTypes.TOAST -> TODO()
            ActionTypes.UNKNOWN -> TODO()
        }
    }

    override fun onCreateInit(savedInstanceState: Bundle?) {
        binding.content.setController(controller = controller)

        viewmodel.getProduct(args.sku)

        observeProductContent()
        observeAddToCartResult()
    }

    private fun observeAddToCartResult() {
        viewmodel.addToCartResult.observe(viewLifecycleOwner) { result ->

            if (result.whichState() != ScreenState.LOADING)
                controller.updateTextLoadingButtonStatusToDefault()

            if (result.whichState() == ScreenState.SUCCESS)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.product_added_to_cart_success),
                    Toast.LENGTH_SHORT
                ).show()

            if (result.whichState() == ScreenState.ERROR)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.product_added_to_cart_fail),
                    Toast.LENGTH_SHORT
                ).show()
        }
    }

    private fun observeProductContent() {
        viewmodel.productResult.observe(viewLifecycleOwner) { result ->
            result.fetchData()?.let {
                val component = it.maxByOrNull { it.spanCount ?: 1 }
                if ((component?.spanCount ?: 1) > 1)
                    binding.content.layoutManager =
                        GridLayoutManager(requireContext(), component?.spanCount ?: 1)
            }

            controller.screenContent = result
        }
    }

}
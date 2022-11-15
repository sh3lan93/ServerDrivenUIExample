package com.example.server_drivenuiexample.ui.offers

import android.net.Uri
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.server_drivenuiexample.R
import com.example.server_drivenuiexample.base.BaseFragment
import com.example.server_drivenuiexample.databinding.FragmentOffersBinding
import com.example.server_drivenuiexample.ui.controller.MainController
import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.utils.ComponentClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class OffersFragment :
    BaseFragment<FragmentOffersBinding, OffersViewModel>(layoutId = R.layout.fragment_offers) {

    override val viewmodel: OffersViewModel by viewModel()

    private val controller: MainController by lazy {
        MainController(buttonClickListener = clickListener)
    }

    private val clickListener: ComponentClickListener = { type, uri ->
        when (type) {
            ActionTypes.DEEP_LINK -> findNavController().navigate(Uri.parse(uri))
            ActionTypes.DISMISS -> TODO()
            ActionTypes.ASYNC_REQUEST -> TODO()
            ActionTypes.TOAST -> TODO()
            ActionTypes.UNKNOWN -> TODO()
        }
    }

    override fun onCreateInit(savedInstanceState: Bundle?) {
        binding.content.setController(controller)
        viewmodel.getOffers()
        observeContent()
    }

    private fun observeContent() {
        viewmodel.offersResult.observe(viewLifecycleOwner) { result ->
            controller.screenContent = result
        }
    }

}
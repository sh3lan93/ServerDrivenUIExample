package com.example.server_drivenuiexample.ui.offers

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.server_drivenuiexample.R
import com.example.server_drivenuiexample.base.BaseFragment
import com.example.server_drivenuiexample.databinding.FragmentOffersBinding
import com.example.server_drivenuiexample.ui.controller.MainController
import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.utils.ComponentClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class OffersFragment :
    BaseFragment<FragmentOffersBinding, OffersViewModel>(layoutId = R.layout.fragment_offers) {

    private val args: OffersFragmentArgs by navArgs()

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
            else -> {}
        }
    }

    override fun onCreateInit(savedInstanceState: Bundle?) {
        binding.content.setController(controller)
        Toast.makeText(requireContext(), "${args.offerId}", Toast.LENGTH_SHORT).show()
        viewmodel.getOffers()
        observeContent()
    }

    private fun observeContent() {
        viewmodel.offersResult.observe(viewLifecycleOwner) { result ->
            controller.screenContent = result
        }
    }

}
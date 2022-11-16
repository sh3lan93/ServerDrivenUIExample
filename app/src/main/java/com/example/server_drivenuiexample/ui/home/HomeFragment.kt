package com.example.server_drivenuiexample.ui.home

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.server_drivenuiexample.R
import com.example.server_drivenuiexample.base.BaseFragment
import com.example.server_drivenuiexample.databinding.FragmentHomeBinding
import com.example.server_drivenuiexample.states.ScreenState
import com.example.server_drivenuiexample.ui.controller.MainController
import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.utils.ComponentClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(layoutId = R.layout.fragment_home) {

    override val viewmodel: HomeViewModel by viewModel()

    private val controller: MainController by lazy {
        MainController(buttonClickListener = clickListener)
    }

    private val clickListener: ComponentClickListener = { type, uri ->
        when (type) {
            ActionTypes.DEEP_LINK -> findNavController().navigate(Uri.parse(uri))
            ActionTypes.DISMISS -> TODO()
            ActionTypes.ASYNC_REQUEST -> TODO()
            ActionTypes.TOAST -> Toast.makeText(
                requireContext(),
                getString(R.string.app_name),
                Toast.LENGTH_SHORT
            ).show()
            ActionTypes.UNKNOWN -> TODO()
            ActionTypes.DIALOG -> {
                showCautionDialog(true)
            }
        }
    }

    override fun onCreateInit(savedInstanceState: Bundle?) {
        viewmodel.getHomeContent()
        binding.root.setOnRefreshListener {
            viewmodel.getHomeContent(isRefreshRequest = true)
        }
        binding.content.layoutManager = LinearLayoutManager(requireContext())
        binding.content.setController(controller)
        observerResult()
    }

    private fun observerResult() {
        viewmodel.homeContentResult.observe(viewLifecycleOwner) { result ->
            result.fetchData()?.let {
                val component = it.maxByOrNull { it.spanCount ?: 1 }
                if ((component?.spanCount ?: 1) > 1)
                    binding.content.layoutManager =
                        GridLayoutManager(requireContext(), component?.spanCount ?: 1)
            }

            if (result.whichState() == ScreenState.LOADING)
                binding.root.isRefreshing = false

            controller.screenContent = result
        }
    }

    private fun showCautionDialog(blocking: Boolean = false) {
        AlertDialog.Builder(requireContext())
            .setCancelable(blocking)
            .setTitle(getString(R.string.caution_dialog_title))
            .setMessage(getString(R.string.caution_dialog_message))
            .apply {
                if (blocking)
                    setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                        dialog.dismiss()
                    }
            }
            .create().also {
                it.show()
            }
    }

}
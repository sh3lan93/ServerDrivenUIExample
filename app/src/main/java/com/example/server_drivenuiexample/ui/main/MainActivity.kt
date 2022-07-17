package com.example.server_drivenuiexample.ui.main

import android.os.Bundle
import android.util.Log
import com.example.server_drivenuiexample.R
import com.example.server_drivenuiexample.base.BaseActivity
import com.example.server_drivenuiexample.databinding.ActivityMainBinding
import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.utils.ComponentClickListener
import com.example.server_drivenuiexample.ui.utils.openUri
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException

class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(layout = R.layout.activity_main) {


    override val viewModel: MainViewModel by viewModel()

    private val controller: MainController by lazy {
        MainController(buttonClickListener = onButtonClicked)
    }

    private val onButtonClicked: ComponentClickListener = { actionType, uri ->
        when (actionType) {
            ActionTypes.DEEP_LINK -> openUri(uri)
            ActionTypes.DISMISS -> TODO()
            ActionTypes.ASYNC_REQUEST -> TODO()
            ActionTypes.UNKNOWN -> TODO()
        }
    }

    override fun onCreateInit(savedInstanceState: Bundle?) {
        val screenContentJson = readJsonFile()
        binding.rvContent.setController(controller)

        screenContentJson?.let {
            viewModel.mapJsonToScreenContent(it)
        }

        viewModel.screenContentResult_.observe(this) {
            controller.screenContent = it
        }
    }

    private fun readJsonFile(): String? {
        var json: String? = null
        try {
            json = assets.open("api_response/main_screen_content.json").bufferedReader().use {
                it.readText()
            }
        } catch (ioException: IOException) {
            Log.e(
                MainActivity::class.java.simpleName,
                "readJsonFile: ${ioException.localizedMessage}",
                ioException.cause
            )
        }
        return json
    }

}
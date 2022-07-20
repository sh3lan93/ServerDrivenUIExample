package com.example.server_drivenuiexample.ui.main

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.server_drivenuiexample.R
import com.example.server_drivenuiexample.base.BaseActivity
import com.example.server_drivenuiexample.databinding.ActivityMainBinding
import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes
import com.example.server_drivenuiexample.ui.utils.ComponentClickListener
import com.example.server_drivenuiexample.ui.utils.PathsConstants
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

    private fun showCautionDialog(blocking: Boolean) {
        AlertDialog.Builder(this)
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

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.data?.let { data ->
            when (data.path) {
                PathsConstants.MAIN_SCREEN_CAUTION_DIALOG_PATH -> showCautionDialog(
                    data.getBooleanQueryParameter(
                        PathsConstants.MAIN_SCREEN_CAUTION_DIALOG_BOCKING_PARAM,
                        false
                    )
                )
                else -> TODO("not implemented yet")
            }
        }
    }

}
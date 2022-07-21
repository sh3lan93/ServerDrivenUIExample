package com.example.server_drivenuiexample.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.server_drivenuiexample.ui.main.MainActivity
import java.io.IOException

abstract class BaseActivity<Binding : ViewDataBinding, ViewModel : BaseViewModel>(
    @LayoutRes protected val layout: Int
) :
    AppCompatActivity() {

    lateinit var binding: Binding

    abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout)
        onCreateInit(savedInstanceState)
    }

    abstract fun onCreateInit(savedInstanceState: Bundle?)

    protected fun readJsonFile(path: String): String? {
        var json: String? = null
        try {
            json = assets.open(path).bufferedReader().use {
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
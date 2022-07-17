package com.example.server_drivenuiexample.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

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

}
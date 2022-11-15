package com.example.server_drivenuiexample.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<Binding : ViewDataBinding, ViewModel : BaseViewModel>(
    @LayoutRes layoutId: Int
) :
    Fragment(layoutId) {

    lateinit var binding: Binding
    abstract val viewmodel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataBindingUtil.bind<Binding>(view)?.let {
            binding = it
        }
        onCreateInit(savedInstanceState)
    }

    abstract fun onCreateInit(savedInstanceState: Bundle?)
}
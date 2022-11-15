package com.example.server_drivenuiexample.ui.main

import android.os.Bundle
import com.example.server_drivenuiexample.R
import com.example.server_drivenuiexample.base.BaseActivity
import com.example.server_drivenuiexample.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(layout = R.layout.activity_main) {


    override val viewModel: MainViewModel by viewModel()


    override fun onCreateInit(savedInstanceState: Bundle?) {

    }

}
package com.example.server_drivenuiexample.ui.epoxy_models

import com.example.server_drivenuiexample.common.clicklisteners.ButtonClickListener


interface ClickableModel {

    fun initClickListener(clickListener: ButtonClickListener)
}
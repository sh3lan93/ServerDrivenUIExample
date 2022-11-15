package com.example.server_drivenuiexample.common.clicklisteners

import com.example.server_drivenuiexample.ui.design_system_language.ActionTypes

interface ButtonClickListener {

    fun onButtonClicked(action: ActionTypes, uri: String)
}
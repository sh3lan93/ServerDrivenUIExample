package com.example.server_drivenuiexample.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri


fun Context.openUri(uri: String) {
    Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(uri)
    }.also {
        this.startActivity(it)
    }
}
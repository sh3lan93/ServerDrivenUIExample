package com.example.server_drivenuiexample.rxutils

import com.example.server_drivenuiexample.states.IError


fun Throwable.mapToError(): IError = object : IError {
    override val cause: String? = this@mapToError.localizedMessage
}
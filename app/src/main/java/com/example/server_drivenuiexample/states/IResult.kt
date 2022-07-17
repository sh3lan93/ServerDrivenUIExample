package com.example.server_drivenuiexample.states

interface IResult<Data> {

    fun whichState(): State

    fun fetchData(): Data?

    fun fetchError(): IError?

}
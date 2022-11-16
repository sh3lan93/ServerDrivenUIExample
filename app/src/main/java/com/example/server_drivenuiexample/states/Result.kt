package com.example.server_drivenuiexample.states

class Result<Data>(
    private val state: State = ScreenState.Uninitialized,
    private val data: Data? = null,
    private val error: IError? = null
) : IResult<Data> {


    companion object {

        fun <Data> loading(): Result<Data> = Result(state = ScreenState.LOADING)

        fun <Data> success(data: Data): Result<Data> =
            Result(state = ScreenState.SUCCESS, data = data)

        fun success(): Result<Any> =
            Result(state = ScreenState.SUCCESS)

        fun <Data> error(error: IError): Result<Data> =
            Result(state = ScreenState.ERROR, error = error)
    }

    override fun whichState(): State = state

    override fun fetchData(): Data? = data

    override fun fetchError(): IError? = error

}

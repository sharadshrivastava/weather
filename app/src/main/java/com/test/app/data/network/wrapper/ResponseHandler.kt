package com.test.app.data.network.wrapper

import com.test.app.R
import com.test.app.WeatherApp
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

open class ResponseHandler {

    companion object {
        fun <T : Any> handleSuccess(data: T): Resource<T> {
            return Resource.success(data)
        }

        fun <T : Any> handleException(e: Exception): Resource<T> {
            return when (e) {
                is HttpException -> Resource.error(
                    getErrorMessage(
                        e.code()
                    ), null)
                is SocketTimeoutException -> Resource.error(getString(R.string.timeout), null)
                is ConnectException -> Resource.error(getString(R.string.connection_error), null)
                else -> Resource.error(
                    getErrorMessage(
                        Int.MAX_VALUE
                    ), null)
            }
        }

        private fun getErrorMessage(code: Int): String {
            return when (code) {
                ErrorCodes.UN_AUTH.code -> getString(R.string.unauthorized)
                ErrorCodes.NOT_FOUND.code -> getString(R.string.not_found)
                ErrorCodes.INTERNAL_ERR.code -> getString(R.string.internal_err)
                ErrorCodes.SVC_UN_AVAIL.code -> getString(R.string.svc_un_avail)
                else -> getString(R.string.response_error)
            }
        }

        private fun getString(id:Int):String = WeatherApp.get()?.getString(id)?:""
    }

    enum class ErrorCodes(val code: Int) {
        UN_AUTH(401),
        NOT_FOUND(404),
        INTERNAL_ERR(500),
        SVC_UN_AVAIL(503)
    }
}
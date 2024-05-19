package com.example.postsapp.utils

sealed class ApiException(message: String? = null) : Exception(message) {

    class TimeOut(val code: String?) : ApiException(code)

    class NoInternetConnection(message: String, throwable: Throwable? = null) :
        ApiException(message)

    data object Unauthorized : ApiException(null)

    class ApiError(
        message: String? = null,
        val statusCode: String? = null,
    ) : ApiException(message)

    class GeneralError(message: String?) : ApiException(message)
}
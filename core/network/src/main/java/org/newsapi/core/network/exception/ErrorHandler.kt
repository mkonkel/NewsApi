package org.newsapi.core.network.exception

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object ErrorHandler {
    fun handle(throwable: Throwable): NetworkException = when (throwable) {
        is HttpException -> {
            when (throwable.code()) {
                401 -> UnauthorizedException("API key is invalid or missing")
                in (400..499) -> NetworkErrorException("Client error: ${throwable.code()}")
                in (500..599) -> ServerException(throwable.code(), "Server error: ${throwable.code()}")
                else -> UnknownException("Unexpected HTTP error: ${throwable.code()}")
            }
        }

        is SocketTimeoutException -> {
            NetworkErrorException("Request timeout")
        }

        is IOException -> {
            NetworkErrorException("Network error: ${throwable.message}")
        }

        else -> {
            UnknownException("Unknown error: ${throwable.message}")
        }
    }
}

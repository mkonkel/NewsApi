package org.newsapi.core.network.utils

import org.newsapi.core.network.exception.ErrorHandler
import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.getOrThrow(): T {
    if (isSuccessful) {
        return body() ?: throw ErrorHandler.handle(IllegalStateException("Response body is null"))
    } else {
        throw HttpException(this)
    }
}

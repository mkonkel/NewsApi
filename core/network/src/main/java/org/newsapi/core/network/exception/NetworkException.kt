package org.newsapi.core.network.exception

sealed class NetworkException(
    message: String? = null,
) : Exception(message)

class UnauthorizedException(
    message: String? = null,
) : NetworkException(message)

class ServerException(
    val code: Int,
    message: String? = null,
) : NetworkException(message)

class NetworkErrorException(
    message: String? = null,
) : NetworkException(message)

class UnknownException(
    message: String? = null,
) : NetworkException(message)

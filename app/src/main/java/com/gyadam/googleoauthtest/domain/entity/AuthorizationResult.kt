package com.gyadam.googleoauthtest.domain.entity

sealed interface AuthorizationResult {

    data class Success(
        val state: AuthorizationState,
    ) : AuthorizationResult

    data class Failure(
        val error: Exception,
    ) : AuthorizationResult

    object BrowserNotAvailable : AuthorizationResult

    object Canceled : AuthorizationResult
}

data class AuthorizationState(
    val value: String? = null,
)

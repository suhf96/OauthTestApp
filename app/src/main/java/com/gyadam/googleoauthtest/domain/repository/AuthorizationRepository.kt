package com.gyadam.googleoauthtest.domain.repository

import android.content.Intent
import com.gyadam.googleoauthtest.data.oauthCore.OAuthConfiguration
import com.gyadam.googleoauthtest.domain.entity.AuthorizationIntentResult
import com.gyadam.googleoauthtest.domain.entity.AuthorizationResult
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse

interface AuthorizationRepository {
    fun getAuthorizationRequestIntent(
        configuration: OAuthConfiguration,
        emailAddress: String,
    ): AuthorizationIntentResult

    suspend fun getAuthorizationResponse(intent: Intent): AuthorizationResponse?
    suspend fun getAuthorizationException(intent: Intent): AuthorizationException?

    suspend fun getExchangeToken(response: AuthorizationResponse): AuthorizationResult
}
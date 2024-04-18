package com.gyadam.googleoauthtest.data.repository

import android.content.Intent
import androidx.core.net.toUri
import com.gyadam.googleoauthtest.data.extensions.toAuthorizationState
import com.gyadam.googleoauthtest.data.oauthCore.OAuthConfiguration
import com.gyadam.googleoauthtest.domain.entity.AuthorizationIntentResult
import com.gyadam.googleoauthtest.domain.entity.AuthorizationResult
import com.gyadam.googleoauthtest.domain.repository.AuthorizationRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.CodeVerifierUtil
import net.openid.appauth.ResponseTypeValues
import timber.log.Timber

class AuthorizationRepositoryImpl(
    private val service: AuthorizationService
) : AuthorizationRepository {
    override fun getAuthorizationRequestIntent(
        configuration: OAuthConfiguration,
        emailAddress: String,
    ): AuthorizationIntentResult {
        return AuthorizationIntentResult.Success(
            createAuthorizationRequestIntent(configuration, emailAddress),
        )
    }

    override suspend fun getAuthorizationResponse(intent: Intent): AuthorizationResponse? {
        return try {
            AuthorizationResponse.fromIntent(intent)
        } catch (e: IllegalArgumentException) {
            Timber.e(e, "Error deserializing AuthorizationResponse")
            null
        }
    }

    override suspend fun getAuthorizationException(intent: Intent): AuthorizationException? {
        return try {
            AuthorizationException.fromIntent(intent)
        } catch (e: IllegalArgumentException) {
            Timber.e(e, "Error deserializing AuthorizationException")
            null
        }
    }

    override suspend fun getExchangeToken(
        response: AuthorizationResponse,
    ): AuthorizationResult = suspendCoroutine { continuation ->
        val tokenRequest = response.createTokenExchangeRequest()

        service.performTokenRequest(tokenRequest) { tokenResponse, authorizationException ->
            val result = if (authorizationException != null) {
                AuthorizationResult.Failure(authorizationException)
            } else if (tokenResponse != null) {
                val authState = AuthState(response, tokenResponse, null)
                AuthorizationResult.Success(authState.toAuthorizationState())
            } else {
                AuthorizationResult.Failure(Exception("Unknown error"))
            }

            continuation.resume(result)
        }
    }
    private fun createAuthorizationRequestIntent(configuration: OAuthConfiguration, emailAddress: String): Intent {
        val serviceConfig = AuthorizationServiceConfiguration(
            configuration.authorizationEndpoint.toUri(),
            configuration.tokenEndpoint.toUri(),
        )

        val authRequestBuilder = AuthorizationRequest.Builder(
            serviceConfig,
            configuration.clientId,
            ResponseTypeValues.CODE,
            configuration.redirectUri.toUri(),
        )

        val codeVerifier = CodeVerifierUtil.generateRandomCodeVerifier()

        val authRequest = authRequestBuilder
            .setScope(configuration.scopes.joinToString(" "))
            .setCodeVerifier(codeVerifier)
            .setLoginHint(emailAddress)
            .build()

        return service.getAuthorizationRequestIntent(authRequest)
    }
}
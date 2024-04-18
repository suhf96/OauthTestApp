package com.gyadam.googleoauthtest.domain.useCases

import com.gyadam.googleoauthtest.data.oauthCore.OAuthConfigurationProvider
import com.gyadam.googleoauthtest.domain.entity.AuthorizationIntentResult
import com.gyadam.googleoauthtest.domain.repository.AuthorizationRepository

class GetOauthRequestIntent {
    operator fun invoke(
        hostname: String,
        email: String,
        repository: AuthorizationRepository,
        configurationProvider: OAuthConfigurationProvider,
    ):
            AuthorizationIntentResult {
        val configuration = configurationProvider.getConfiguration(hostname)
            ?: return AuthorizationIntentResult.NotSupported

        return repository.getAuthorizationRequestIntent(configuration, email)
    }

}
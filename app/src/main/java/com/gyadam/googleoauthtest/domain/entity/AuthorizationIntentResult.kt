package com.gyadam.googleoauthtest.domain.entity

import android.content.Intent

sealed interface AuthorizationIntentResult {
    object NotSupported : AuthorizationIntentResult

    data class Success(
        val intent: Intent,
    ) : AuthorizationIntentResult
}

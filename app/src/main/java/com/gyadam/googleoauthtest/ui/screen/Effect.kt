package com.gyadam.googleoauthtest.ui.screen

import android.content.Intent
import com.gyadam.googleoauthtest.domain.entity.AuthorizationState

sealed interface Effect {
    data class LaunchOAuth(
        val intent: Intent,
    ) : Effect

    data class NavigateNext(
        val state: AuthorizationState,
    ) : Effect
    object NavigateBack : Effect
}
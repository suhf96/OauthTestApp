package com.gyadam.googleoauthtest.ui.screen

import android.content.Intent

sealed class SIgnInEvent {

    data class singInWithGoogle(val email: String) : SIgnInEvent()

    data class OnOAuthResult(
        val resultCode: Int,
        val data: Intent?,
    ) : SIgnInEvent()
}
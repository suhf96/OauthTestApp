package com.gyadam.googleoauthtest.ui.screen

sealed class SIgnInEvent {

    data class singInWithGoogle(val email: String) : SIgnInEvent()
}
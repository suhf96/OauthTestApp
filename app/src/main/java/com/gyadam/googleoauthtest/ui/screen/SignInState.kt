package com.gyadam.googleoauthtest.ui.screen

import android.content.Intent

data class SignInState(
    val isGoogleSignIn : Boolean = false,
    val notSupported : Boolean = false,
    val successFuls : Boolean = false,
    val isLoading:Boolean = false,
    val resultIntent : Intent? =null
)

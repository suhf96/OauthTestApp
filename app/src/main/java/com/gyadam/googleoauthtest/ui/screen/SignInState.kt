package com.gyadam.googleoauthtest.ui.screen

data class SignInState(
    val isGoogleSignIn : Boolean = false,
    val notSupported : Boolean = false,
    val successFuls : Boolean = false,
    val isLoading:Boolean = false
)

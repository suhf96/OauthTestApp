package com.gyadam.googleoauthtest.ui.screen

import androidx.lifecycle.ViewModel
import com.gyadam.googleoauthtest.domain.useCases.GetOauthRequestIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val getOAuthRequestIntent: GetOauthRequestIntent,
) : ViewModel() {

    fun onEvent(event: SIgnInEvent) {
        when (event) {
            is SIgnInEvent.singInWithGoogle -> {
                getOAuthRequestIntent(event.email)
            }
        }
    }
}
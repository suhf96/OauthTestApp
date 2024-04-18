package com.gyadam.googleoauthtest.ui.screen

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import com.gyadam.googleoauthtest.data.oauthCore.OAuthConfigurationProvider
import com.gyadam.googleoauthtest.domain.entity.AuthorizationIntentResult
import com.gyadam.googleoauthtest.domain.repository.AuthorizationRepository
import com.gyadam.googleoauthtest.domain.useCases.GetOauthRequestIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val getOAuthRequestIntent: GetOauthRequestIntent,
    private val repository: AuthorizationRepository,
    private val configurationFactory: OAuthConfigurationProvider,
) : ViewModel() {


    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state.asStateFlow()
    fun onEvent(event: SIgnInEvent) {
        when (event) {
            is SIgnInEvent.singInWithGoogle -> {

                _state.update {
                    it.copy(isLoading = true)
                }
                val result = getOAuthRequestIntent(
                    "imap.gmail.com",
                    event.email,
                    repository,
                    configurationFactory
                )

                when (result) {
                    AuthorizationIntentResult.NotSupported -> {
                        _state.update {
                            it.copy(
                                notSupported = true,
                                isLoading = false
                            )
                        }
                    }

                    is AuthorizationIntentResult.Success -> {
                        println("RESULT INTENT : ${result.intent}")

                        println("RESULT INTENT EXTRAS: ${result.intent.extras}")
                        _state.update {
                            it.copy(
                                resultIntent = result.intent,
                                notSupported = false,
                                successFuls = true,
                                isLoading = false
                            )
                        }
                    }
                }
            }

            is SIgnInEvent.OnOAuthResult -> onOAuthResult(event.resultCode, event.data)
        }
    }

    private fun onOAuthResult(resultCode: Int, data: Intent?) {
        /*if (resultCode == Activity.RESULT_OK && data != null) {
            finishSignIn(data)
        } else {
            updateState { state ->
                state.copy(error = Error.Canceled)
            }
        }*/
    }
}
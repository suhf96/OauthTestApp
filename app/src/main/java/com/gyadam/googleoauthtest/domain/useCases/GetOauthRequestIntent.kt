package com.gyadam.googleoauthtest.domain.useCases

class GetOauthRequestIntent {
    operator fun invoke(email: String) {
        println("Sign in with email : $email")
    }
}
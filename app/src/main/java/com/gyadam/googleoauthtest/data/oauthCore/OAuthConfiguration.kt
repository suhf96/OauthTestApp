package com.gyadam.googleoauthtest.data.oauthCore

data class OAuthConfiguration(
    val clientId: String,
    val scopes: List<String>,
    val authorizationEndpoint: String,
    val tokenEndpoint: String,
    val redirectUri: String,
)

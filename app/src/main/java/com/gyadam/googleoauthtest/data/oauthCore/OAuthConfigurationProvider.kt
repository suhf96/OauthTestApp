package com.gyadam.googleoauthtest.data.oauthCore

fun interface OAuthConfigurationProvider {
    fun getConfiguration(hostname: String): OAuthConfiguration?
}

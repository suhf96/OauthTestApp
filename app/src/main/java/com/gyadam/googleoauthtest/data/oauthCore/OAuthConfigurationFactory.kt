package com.gyadam.googleoauthtest.data.oauthCore

fun interface OAuthConfigurationFactory {
    fun createConfigurations(): Map<List<String>, OAuthConfiguration>
}

package com.gyadam.googleoauthtest.module

import com.gyadam.googleoauthtest.data.oauthCore.InMemoryOAuthConfigurationProvider
import com.gyadam.googleoauthtest.data.oauthCore.OAuthConfigurationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

}
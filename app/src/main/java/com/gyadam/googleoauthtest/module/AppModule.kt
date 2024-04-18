package com.gyadam.googleoauthtest.module

import android.content.Context
import com.gyadam.googleoauthtest.data.oauthCore.InMemoryOAuthConfigurationProvider
import com.gyadam.googleoauthtest.data.oauthCore.OAuthConfigurationFactory
import com.gyadam.googleoauthtest.data.oauthCore.OAuthConfigurationFactoryImpl
import com.gyadam.googleoauthtest.data.oauthCore.OAuthConfigurationProvider
import com.gyadam.googleoauthtest.data.repository.AuthorizationRepositoryImpl
import com.gyadam.googleoauthtest.domain.repository.AuthorizationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import net.openid.appauth.AuthorizationService

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideOauthConfigurationFactory(): OAuthConfigurationFactory =
        OAuthConfigurationFactoryImpl()


    @Provides
    @Singleton
    fun provideOauthConfiguration(configurationFactory: OAuthConfigurationFactory): OAuthConfigurationProvider =
        InMemoryOAuthConfigurationProvider(configurationFactory)

    @Singleton
    @Provides
    fun provideAuthorizationRepository(context: Context): AuthorizationRepository =
        AuthorizationRepositoryImpl(service = AuthorizationService(context))
}
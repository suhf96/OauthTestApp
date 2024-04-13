package com.gyadam.googleoauthtest.module

import com.gyadam.googleoauthtest.domain.useCases.GetOauthRequestIntent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideGetOAutheRequestIntentUseCase(): GetOauthRequestIntent = GetOauthRequestIntent()
}
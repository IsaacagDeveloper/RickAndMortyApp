package com.isaacag.core.di

import com.isaacag.core.core.CoroutineDispatcherProvider
import com.isaacag.core.core.DefaultCoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatchersModule {

    @Provides
    @Singleton
    fun providesDispatcherProvider(): CoroutineDispatcherProvider =
        DefaultCoroutineDispatcherProvider()

}
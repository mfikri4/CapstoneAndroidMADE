@file:Suppress("unused")

package com.mfikri.capstoneandroidexpert.di

import com.mfikri.core.domain.usecase.WatchInteractor
import com.mfikri.core.domain.usecase.WatchUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideWatchUseCase(watchInteractor: WatchInteractor): WatchUseCase
}
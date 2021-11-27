package com.android.sample.di

import com.android.sample.data.ShowRepository
import com.android.sample.data.ShowRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    internal abstract fun bindRepository(dashboardRepository: ShowRepositoryImpl): ShowRepository
}
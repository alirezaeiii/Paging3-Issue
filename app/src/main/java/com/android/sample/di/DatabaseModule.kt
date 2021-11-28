package com.android.sample.di

import android.content.Context
import androidx.room.Room
import com.android.sample.db.ShowDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideShowDatabase(context: Context): ShowDatabase {
        return Room.databaseBuilder(
            context,
            ShowDatabase::class.java,
            "Show.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideShowDao(db: ShowDatabase) = db.showDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(db: ShowDatabase) = db.remoteKeysDao()
}
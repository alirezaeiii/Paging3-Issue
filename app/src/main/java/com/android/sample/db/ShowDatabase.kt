package com.android.sample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.sample.model.Show


@Database(
    entities = [Show::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class ShowDatabase : RoomDatabase() {

    abstract fun showDao(): ShowDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}
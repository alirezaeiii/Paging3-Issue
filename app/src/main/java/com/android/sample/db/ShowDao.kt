package com.android.sample.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.sample.model.Show

@Dao
interface ShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<Show>)

    @Query("SELECT * FROM shows")
    fun getShows() : PagingSource<Int, Show>

    @Query("DELETE FROM shows")
    suspend fun clearShows()
}
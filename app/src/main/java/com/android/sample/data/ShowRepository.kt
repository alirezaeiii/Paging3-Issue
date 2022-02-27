package com.android.sample.data

import androidx.paging.PagingData
import com.android.sample.model.Show
import kotlinx.coroutines.flow.Flow

interface ShowRepository {
    val fetchResultStream: Flow<PagingData<Show>>
}
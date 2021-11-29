package com.android.sample.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.sample.db.ShowDatabase
import com.android.sample.model.Show
import com.android.sample.network.ShowService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowRepositoryImpl @Inject constructor(
    private val service: ShowService,
    private val database: ShowDatabase
) : ShowRepository {

    @OptIn(ExperimentalPagingApi::class)
    override val fetchResultStream: Flow<PagingData<Show>>
        get() = Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            remoteMediator = ShowRemoteMediator(service, database),
            pagingSourceFactory = { database.showDao().getShows() }
        ).flow


    companion object {
        private const val NETWORK_PAGE_SIZE = 250
    }
}
package com.android.sample.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.sample.model.Show
import com.android.sample.network.ShowService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowRepositoryImpl @Inject constructor(private val service: ShowService) : ShowRepository {

    override val fetchResultStream: Flow<PagingData<Show>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
        pagingSourceFactory = { ShowPagingSource(service) }
    ).flow


    companion object {
        const val NETWORK_PAGE_SIZE = 15
    }
}

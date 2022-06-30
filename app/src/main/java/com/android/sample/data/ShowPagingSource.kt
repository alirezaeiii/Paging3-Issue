package com.android.sample.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.sample.model.Show
import com.android.sample.network.ShowService
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class ShowPagingSource(
    private val service: ShowService
) : PagingSource<Int, Show>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.fetchShowList(page)
            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, Show>): Int? = null
}

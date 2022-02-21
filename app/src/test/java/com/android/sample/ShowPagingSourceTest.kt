package com.android.sample

import androidx.paging.PagingSource.LoadParams.Refresh
import androidx.paging.PagingSource.LoadResult.Page
import com.android.sample.data.ShowPagingSource
import com.android.sample.model.Image
import com.android.sample.model.Show
import com.android.sample.network.ShowService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ShowPagingSourceTest {

    @Mock
    private lateinit var api: ShowService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun pageKeyedShowPagingSource() = runBlockingTest {
        val pagingSource = ShowPagingSource(api)

        val show1 = Show(
            1, "name1",
            Image("medium1", "original1"), "summary1"
        )
        val show2 = Show(
            2, "name2",
            Image("medium2", "original2"), "summary2"
        )

        `when`(api.fetchShowList(1)).thenReturn(listOf(show1, show2))

        assertEquals(
            expected = Page(
                data = listOf(show1, show2),
                prevKey = null,
                nextKey = 2
            ),
            actual = pagingSource.load(
                Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        )
    }
}
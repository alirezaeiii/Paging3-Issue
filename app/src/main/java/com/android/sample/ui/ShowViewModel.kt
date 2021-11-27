package com.android.sample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.sample.data.ShowRepository
import com.android.sample.model.Show
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(
    repository: ShowRepository
) : ViewModel() {

    val pagingDataFlow: Flow<PagingData<Show>> =
        repository.fetchResultStream.cachedIn(viewModelScope)
}
package com.android.sample.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.android.sample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ShowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val showAdapter = ShowAdapter()
        binding.list.apply {
            val layoutManager = layoutManager as GridLayoutManager
            layoutManager.spanSizeLookup = object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (showAdapter.getItemViewType(position) == ShowAdapter.LOADING_ITEM)
                        1 else layoutManager.spanCount
                }
            }
            adapter = showAdapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapter { showAdapter.retry() },
                footer = LoadStateAdapter { showAdapter.retry() }
            )
        }
        binding.retryButton.setOnClickListener { showAdapter.retry() }

        lifecycleScope.launch {
            viewModel.pagingDataFlow.collectLatest(showAdapter::submitData)
        }

        binding.bindList(
            showAdapter = showAdapter
        )
    }

    private fun ActivityMainBinding.bindList(
        showAdapter: ShowAdapter
    ) {
        lifecycleScope.launch {
            showAdapter.loadStateFlow.collect { loadState ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && showAdapter.itemCount == 0
                    // show empty list
                emptyList.isVisible = isListEmpty
                // Only show the list if refresh succeeds, either from the the local db or the remote.
                list.isVisible = loadState.source.refresh is LoadState.NotLoading || loadState.mediator?.refresh is LoadState.NotLoading
                // Show loading spinner during initial load or refresh.
                progressBar.isVisible = loadState.mediator?.refresh is LoadState.Loading
                // Show the retry state if initial load or refresh fails.
                retryButton.isVisible = loadState.mediator?.refresh is LoadState.Error && showAdapter.itemCount == 0
            }
        }
    }
}
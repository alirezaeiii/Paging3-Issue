package com.android.sample.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.android.sample.model.Show

class ShowAdapter : PagingDataAdapter<Show, ShowViewHolder>(SHOW_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        return ShowViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) SHOW_ITEM else LOADING_ITEM
    }

    companion object {
        private val SHOW_COMPARATOR = object : DiffUtil.ItemCallback<Show>() {
            override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean =
                oldItem == newItem
        }
        private const val SHOW_ITEM = 0
        const val LOADING_ITEM = 1
    }
}
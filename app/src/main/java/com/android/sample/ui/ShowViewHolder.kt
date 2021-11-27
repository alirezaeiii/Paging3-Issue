package com.android.sample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.sample.databinding.ShowItemBinding
import com.android.sample.model.Show

class ShowViewHolder(private val binding: ShowItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(show: Show) {
        with(binding) {
            itemPosterPost.load(show.image?.medium)
            itemPosterTitle.text = show.name
        }
    }

    companion object {
        fun create(parent: ViewGroup): ShowViewHolder {
            val binding = ShowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ShowViewHolder(binding)
        }
    }
}
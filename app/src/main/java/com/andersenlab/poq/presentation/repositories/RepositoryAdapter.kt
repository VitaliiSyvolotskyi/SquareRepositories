package com.andersenlab.poq.presentation.repositories

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RepositoryAdapter : ListAdapter<Repository, RepositoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(RepositoryItemView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
        })

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        (holder.itemView as RepositoryItemView).setItem(getItem(position))

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem == newItem

        }
    }
}
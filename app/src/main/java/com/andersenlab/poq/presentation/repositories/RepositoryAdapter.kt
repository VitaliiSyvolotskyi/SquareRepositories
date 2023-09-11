package com.andersenlab.poq.presentation.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andersenlab.poq.databinding.ItemSquareReposBinding
import com.andersenlab.poq.domain.model.Repository

class RepositoryAdapter :
    ListAdapter<Repository, RepositoryAdapter.RepositoryViewHolder>(DIFF_CALLBACK) {
    class RepositoryViewHolder(var binding: ItemSquareReposBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder =
        RepositoryViewHolder(
            ItemSquareReposBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            nameTV.text = item.name
            descriptionTV.isVisible = item.description.isNotBlank()
            descriptionTV.text = item.description
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem == newItem

        }
    }
}
package com.andersenlab.poq.presentation.repositories

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.andersenlab.poq.databinding.ItemSquareReposBinding

class RepositoryItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding =
        ItemSquareReposBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    fun setItem(item: Repository) {
        with(binding) {
            nameTV.text = item.name
            descriptionTV.isVisible = item.description.isNotBlank()
            descriptionTV.text = item.description
        }
    }
}
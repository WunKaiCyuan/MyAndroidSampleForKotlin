package com.cyuan.mysampleforkotlin.sample.instagram.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cyuan.mysampleforkotlin.R
import com.cyuan.mysampleforkotlin.databinding.ItemInstagramPostBinding
import com.cyuan.mysampleforkotlin.sample.instagram.viewmodel.HomeViewModel

class PostViewHolder(private val binding: ItemInstagramPostBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: HomeViewModel, index: Int) {
        binding.viewModel = viewModel
        binding.index = index
    }

    companion object {
        fun create(parent: ViewGroup, viewModel: HomeViewModel): PostViewHolder {
            val binding =
                DataBindingUtil.inflate<ItemInstagramPostBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_instagram_post,
                    parent,
                    false
                )
            return PostViewHolder(
                binding
            )
        }
    }
}
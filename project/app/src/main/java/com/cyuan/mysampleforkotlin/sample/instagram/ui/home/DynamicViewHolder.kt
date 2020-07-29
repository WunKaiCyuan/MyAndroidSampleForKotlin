package com.cyuan.mysampleforkotlin.sample.instagram.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cyuan.mysampleforkotlin.R
import com.cyuan.mysampleforkotlin.databinding.ItemInstagramDynamicBinding
import com.cyuan.mysampleforkotlin.sample.instagram.viewmodel.HomeViewModel

class DynamicViewHolder(private val binding: ItemInstagramDynamicBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: HomeViewModel, index: Int) {
        binding.viewModel = viewModel
        binding.index = index
    }

    companion object {
        fun create(parent: ViewGroup): DynamicViewHolder {
            val binding =
                DataBindingUtil.inflate<ItemInstagramDynamicBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_instagram_dynamic,
                    parent,
                    false
                )

            return DynamicViewHolder(
                binding
            )
        }
    }
}
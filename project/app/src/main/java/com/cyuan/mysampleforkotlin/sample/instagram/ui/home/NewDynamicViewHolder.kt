package com.cyuan.mysampleforkotlin.sample.instagram.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cyuan.mysampleforkotlin.R
import com.cyuan.mysampleforkotlin.databinding.ItemInstagramNewDynamicBinding
import com.cyuan.mysampleforkotlin.sample.instagram.viewmodel.HomeViewModel

class NewDynamicViewHolder(private val binding: ItemInstagramNewDynamicBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(viewModel: HomeViewModel) {
        binding.viewModel = viewModel
    }

    companion object {
        fun create(parent: ViewGroup): NewDynamicViewHolder {
            val binding =
                DataBindingUtil.inflate<ItemInstagramNewDynamicBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_instagram_new_dynamic,
                    parent,
                    false
                )

            return NewDynamicViewHolder(
                binding
            )
        }
    }
}
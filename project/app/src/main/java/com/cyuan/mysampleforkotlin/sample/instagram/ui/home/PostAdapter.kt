package com.cyuan.mysampleforkotlin.sample.instagram.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyuan.mysampleforkotlin.sample.instagram.viewmodel.HomeViewModel

class PostAdapter(private val viewModel: HomeViewModel) : RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.create(
            parent,
            viewModel
        )
    }

    override fun getItemCount(): Int {
        return viewModel.postList.value!!.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }
}
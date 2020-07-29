package com.cyuan.mysampleforkotlin.sample.instagram.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyuan.mysampleforkotlin.sample.instagram.viewmodel.HomeViewModel

class DynamicAdapter(val viewModel: HomeViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TYPE_NEW_DYNAMIC -> NewDynamicViewHolder.create(
                parent
            )
            else -> DynamicViewHolder.create(
                parent
            )
        }
    }

    override fun getItemCount(): Int {
        return viewModel.dynamicPostList.value!!.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DynamicViewHolder)
            holder.bind(viewModel, position - 1)
        else if(holder is NewDynamicViewHolder)
            holder.bind(viewModel)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            TYPE_NEW_DYNAMIC
        else
            TYPE_DYNAMIC
    }

    companion object {
        const val TYPE_NEW_DYNAMIC = 1
        const val TYPE_DYNAMIC = 2
    }
}
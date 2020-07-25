package com.cyuan.mysampleforkotlin.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyuan.mysampleforkotlin.R

class SampleListAdapter(private val data: List<SampleInfo<*>>) :
    RecyclerView.Adapter<SampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sample, parent, false)
        return SampleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(data.get(position))
    }
}
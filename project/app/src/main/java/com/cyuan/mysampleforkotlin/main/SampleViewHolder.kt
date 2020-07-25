package com.cyuan.mysampleforkotlin.main

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyuan.mysampleforkotlin.R
import com.cyuan.mysampleforkotlin.sample.VideoControllerActivity
import kotlinx.android.synthetic.main.item_sample.view.*

class SampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private lateinit var data: SampleInfo<*>

    fun bind(data: SampleInfo<*>) {
        this.data = data
        itemView.tvSubject.text = data.displayName
        when (data.status) {
            SampleInfo.STATUS_INIT -> {
                itemView.tvStatus.text = "準備中"
                itemView.btnGo.visibility = View.INVISIBLE
            }
            SampleInfo.STATUS_PADDING -> {
                itemView.tvStatus.text = "製作中"
                itemView.btnGo.visibility = View.INVISIBLE
            }
            SampleInfo.STATUS_FINISH -> {
                itemView.tvStatus.text = "已完成"
                itemView.btnGo.visibility = View.VISIBLE
            }
        }

        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            itemView.btnGo -> {
                val intent = Intent(itemView.context, data.activity)
                itemView.context.startActivity(intent)
            }
        }
    }
}
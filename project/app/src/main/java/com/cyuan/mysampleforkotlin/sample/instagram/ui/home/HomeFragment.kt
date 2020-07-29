package com.cyuan.mysampleforkotlin.sample.instagram.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyuan.mysampleforkotlin.R
import com.cyuan.mysampleforkotlin.sample.instagram.repository.HomeRepository
import com.cyuan.mysampleforkotlin.sample.instagram.viewmodel.HomeViewModel
import com.cyuan.mysampleforkotlin.sample.instagram.viewmodel.HomeViewModelFactory
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() =
            HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val repository =
            HomeRepository()
        val factory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        rvDynamicList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvDynamicList.adapter =
            DynamicAdapter(
                viewModel
            )

        viewModel.loadDynamicPostList()
        viewModel.dynamicPostList.observe(viewLifecycleOwner, Observer {
            rvDynamicList.adapter!!.notifyDataSetChanged()
        })


        rvPostList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvPostList.adapter =
            PostAdapter(
                viewModel
            )

        viewModel.loadPostList()
        viewModel.postList.observe(viewLifecycleOwner, Observer {
            rvPostList.adapter!!.notifyDataSetChanged()
        })
        viewModel.postUpdateIndex.observe(viewLifecycleOwner, Observer {
            rvPostList.adapter!!.notifyItemChanged(it)
        })
    }

}


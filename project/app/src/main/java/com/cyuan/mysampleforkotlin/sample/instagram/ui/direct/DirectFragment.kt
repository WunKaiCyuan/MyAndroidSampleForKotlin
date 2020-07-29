package com.cyuan.mysampleforkotlin.sample.instagram.ui.direct

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cyuan.mysampleforkotlin.R
import com.cyuan.mysampleforkotlin.sample.instagram.viewmodel.DirectViewModel

class DirectFragment : Fragment() {

    companion object {
        fun newInstance() =
            DirectFragment()
    }

    private lateinit var viewModel: DirectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.direct_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DirectViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
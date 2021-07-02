package com.ammar.mediaapp.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ammar.mediaapp.R
import com.ammar.mediaapp.util.Constants.Companion.TAB_LIST
import com.ammar.mediaapp.view.activity.MainActivity
import com.ammar.mediaapp.view.adapter.TabFragmentAdapter
import com.ammar.mediaapp.view.viewmodel.NewsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_recommend_news.*
import kotlinx.android.synthetic.main.fragment_recommend_news.view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class RecommendNewsFragment : Fragment(R.layout.fragment_recommend_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var tabFragmentAdapter: TabFragmentAdapter
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).vm

        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tablayout)
        tabFragmentAdapter = TabFragmentAdapter(this)
        viewPager.adapter = tabFragmentAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = TAB_LIST[position].uppercase()
        }.attach()

        var job: Job? = null
        btnRefresh.setOnClickListener {
            job?.cancel()
            job = MainScope().launch {
                viewModel.getTabNews()
                Toast.makeText(
                    activity,
                    "Refreshed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
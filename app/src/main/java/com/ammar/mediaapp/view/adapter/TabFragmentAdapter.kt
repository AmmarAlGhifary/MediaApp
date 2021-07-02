package com.ammar.mediaapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ammar.mediaapp.util.Constants.Companion.TAB_LIST
import com.ammar.mediaapp.view.fragment.TabNewsFragment

class TabFragmentAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return TAB_LIST.size
    }

    override fun createFragment(position: Int): Fragment {
        return TabNewsFragment(position)
    }
}

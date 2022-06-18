package com.example.gramoday.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gramoday.ui.fragment.BusinessFragment
import com.example.gramoday.ui.fragment.ReviewFragment

//view Pager Adapter to do swipe fragments in main activity

private const val NUM_TABS = 2

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return BusinessFragment()
            1 -> return ReviewFragment()
        }
        return BusinessFragment()
    }

}
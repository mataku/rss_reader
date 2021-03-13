package com.example.rssreader.ui.widget.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rssreader.ui.fragment.FeedFragment

class FeedPagerAdapter(fragmentActivity: FragmentActivity, private val feedCategoryList: List<String>) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return feedCategoryList.size
    }

    override fun createFragment(position: Int): Fragment {
        return FeedFragment(feedCategoryList[position])
    }
}
package com.example.assignmnet_img.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.assignmnet_img.R
import com.example.assignmnet_img.bookmark.BookMarkFragment
import com.example.assignmnet_img.search.SearchFragment
import java.lang.IllegalStateException

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = mutableListOf<TabModel>()

    init {
        fragments.add(TabModel(SearchFragment(), R.string.main_tab_search_title))
        fragments.add(TabModel(BookMarkFragment(), R.string.main_tab_bookmark_title))
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchFragment()
            1 -> BookMarkFragment()
            else -> throw IllegalStateException()
        }
    }

    fun getTitle(position: Int): Int {
        return fragments[position].titleRes
    }
}
package com.unero.moviecatalogue.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.unero.moviecatalogue.ui.home.tabs.movie.MovieFragment
import com.unero.moviecatalogue.ui.home.tabs.tv.TVShowFragment
import com.unero.moviecatalogue.util.Constant.TAB_TITLES

class HomePagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> MovieFragment()
            1 -> TVShowFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int = 2
}
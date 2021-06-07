package com.unero.moviecatalogue.ui.favorite

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.unero.moviecatalogue.ui.favorite.tab.movie.MovieFavoriteFragment
import com.unero.moviecatalogue.ui.favorite.tab.tv.TVFavoriteFragment
import com.unero.moviecatalogue.util.Constant.TAB_TITLES

class FavoritePagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> MovieFavoriteFragment()
            1 -> TVFavoriteFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int = TAB_TITLES.size
}
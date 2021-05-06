package com.medialink.archcompsubmission.ui.main

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.medialink.archcompsubmission.R

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLE = intArrayOf(R.string.movie, R.string.tv_show)
    }

    override fun getCount(): Int = TAB_TITLE.size

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment.newInstance(MovieFragment.PARAM_MOVIE)
            1 -> MovieFragment.newInstance(MovieFragment.PARAM_TV_SHOW)
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLE[position])
    }
}
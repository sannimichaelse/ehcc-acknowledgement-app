package com.base

import android.os.Parcelable
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by tomiwa on 20:35 9/1/18
 */
open class ViewPagerAdapter(
        fm: FragmentManager,
        private val fragmentList: List<androidx.fragment.app.Fragment>,
        private val titles: List<String>
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return this.fragmentList[position]
    }

    override fun getCount(): Int {
        return this.fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return this.titles[position]
    }

    override fun saveState(): Parcelable? {
        return null
    }
}

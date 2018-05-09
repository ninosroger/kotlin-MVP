package com.ninos.mvp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by ninos on 2017/6/10.
 */
class NavigateViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    var mFragments: ArrayList<Fragment> = ArrayList()
    var mFragmentsTitiles: ArrayList<String> = ArrayList()

    override fun getItem(p: Int): Fragment = mFragments[p]

    override fun getCount(): Int = mFragments.size

    fun addFragment(fragment: Fragment, fragmentTitle: String) {
        mFragments.add(fragment)
        mFragmentsTitiles.add(fragmentTitle)
    }

    override fun getPageTitle(position: Int): CharSequence = mFragmentsTitiles[position]
}
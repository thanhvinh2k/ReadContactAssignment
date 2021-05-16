package com.thanhvinh.readcontactassignment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.thanhvinh.readcontactassignment.ContactFragment
import com.thanhvinh.readcontactassignment.DiaryFragment

class PagerAdapter(fragmentManager: FragmentManager, numberOfTabs: Int) :
        FragmentStatePagerAdapter(fragmentManager) {

    private var mNumberOfTabs = 0
    init {
        mNumberOfTabs = numberOfTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ContactFragment()
            1 -> DiaryFragment()
            else -> DiaryFragment()
        }
    }

    override fun getCount(): Int {
        return mNumberOfTabs
    }
}

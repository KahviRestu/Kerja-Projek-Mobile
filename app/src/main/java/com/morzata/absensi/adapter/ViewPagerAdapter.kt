package com.morzata.absensi.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.morzata.absensi.fragment.MapsFragment
import com.morzata.absensi.fragment.ResultFragment

class ViewPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> MapsFragment()
            else -> return ResultFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Absen"
            1 -> "Result"
            else -> return null
        }
    }

}
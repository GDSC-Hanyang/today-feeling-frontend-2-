package com.naze.todayfeeling.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.naze.todayfeeling.presentation.writing.WritingFragment1
import com.naze.todayfeeling.presentation.writing.WritingFragment2
import com.naze.todayfeeling.presentation.writing.WritingFragment3

class FeelingFragmentAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {

    private val fragmentList = listOf<Fragment>( WritingFragment1(), WritingFragment2(), WritingFragment3())

    override fun getItemCount() : Int{
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}
package com.naze.todayfeeling.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.naze.todayfeeling.R
import com.naze.todayfeeling.databinding.FragmentHomeBinding
import com.naze.todayfeeling.presentation.calendar.AdapterMonth
import com.naze.todayfeeling.presentation.writing.WritingActivity
import com.naze.todayfeeling.util.binding.BindingFragment

class HomeFragment: BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        val monthListManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val monthListAdapter = AdapterMonth()

        binding.rvCalendarHome.apply {
            layoutManager = monthListManager
            adapter = monthListAdapter
            scrollToPosition(Int.MAX_VALUE/2)
        }

        binding.btnRecordHome.setOnClickListener {
            val intent = Intent(requireActivity(), WritingActivity::class.java)
            startActivity(intent)
        }

        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(binding.rvCalendarHome)
    }
}
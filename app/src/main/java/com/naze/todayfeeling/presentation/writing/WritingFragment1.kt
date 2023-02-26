package com.naze.todayfeeling.presentation.writing

import android.os.Bundle
import android.view.View
import com.naze.todayfeeling.R
import com.naze.todayfeeling.databinding.FragmentWriting1Binding
import com.naze.todayfeeling.util.binding.BindingFragment

class WritingFragment1:BindingFragment<FragmentWriting1Binding>(R.layout.fragment_writing_1) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()
    }

    private fun initLayout() {
        binding.flPositive.setOnClickListener {
            toggleSetting(true)
        }
        binding.flNegative.setOnClickListener {
            toggleSetting(false)
        }
    }

    private fun toggleSetting(state: Boolean) {
        binding.apply {
            flPositive.isSelected = state
            btnCheckPositive.isSelected = state
            tvFeelingPositive.isSelected = state
            flPositive.elevation = 4F
            flNegative.isSelected = !state
            btnCheckNegative.isSelected = !state
            tvFeelingNegative.isSelected = !state
            flNegative.elevation = 4F
        }
    }
}
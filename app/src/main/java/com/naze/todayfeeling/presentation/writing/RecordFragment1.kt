package com.naze.todayfeeling.presentation.writing

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.naze.todayfeeling.R
import com.naze.todayfeeling.databinding.FragmentRecord1Binding
import com.naze.todayfeeling.util.binding.BindingFragment

class RecordFragment1:BindingFragment<FragmentRecord1Binding>(R.layout.fragment_record_1) {
    private lateinit var viewModel : RecordViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[RecordViewModel::class.java]
        initLayout()
    }

    private fun initLayout() {
        binding.flPositive.setOnClickListener {
            toggleSetting(true)
            viewModel.changeSelectStateFlow()
        }
        binding.flNegative.setOnClickListener {
            toggleSetting(false)
            viewModel.changeSelectStateFlow()
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
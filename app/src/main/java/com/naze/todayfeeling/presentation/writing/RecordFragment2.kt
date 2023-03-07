package com.naze.todayfeeling.presentation.writing

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.naze.todayfeeling.R
import com.naze.todayfeeling.databinding.FragmentRecord2Binding
import com.naze.todayfeeling.util.binding.BindingFragment

class RecordFragment2:BindingFragment<FragmentRecord2Binding>(R.layout.fragment_record_2) {
    private lateinit var viewModel : RecordViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[RecordViewModel::class.java]
        initLayout()
    }

    private fun initLayout() {
        binding.btnRecord.setOnClickListener {
            viewModel.changeRecordStateFlow()
        }
    }


}
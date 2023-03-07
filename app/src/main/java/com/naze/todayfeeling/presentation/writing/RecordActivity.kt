package com.naze.todayfeeling.presentation.writing

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.naze.todayfeeling.R
import com.naze.todayfeeling.databinding.ActivityWritingBinding
import com.naze.todayfeeling.util.RecordFragmentAdapter
import com.naze.todayfeeling.util.binding.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class RecordActivity : BindingActivity<ActivityWritingBinding>(R.layout.activity_writing) {
    private val viewModel : RecordViewModel by viewModels()
    private val adapter = RecordFragmentAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        initViewModel()
        initObserver()
    }

    private fun initLayout() {
        binding.viewPager.adapter = adapter
    }

    private fun initViewModel() {
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver() {
        viewModel.selectState.flowWithLifecycle(lifecycle).onEach {
            if (it) {
                Log.d("Fragment Test","selectState- = ${viewModel.selectState.value}")
                adapter.fragmentList.add(1,RecordFragment2())
                adapter.notifyItemInserted(1)
                binding.viewPager.currentItem = 1
            }
        }.launchIn(lifecycleScope)

        viewModel.recordState.flowWithLifecycle(lifecycle).onEach {
            if (it) {
                Log.d("Fragment Test","recordState- = ${viewModel.recordState.value}")
                adapter.fragmentList.add(2,RecordFragment3())
                adapter.notifyDataSetChanged()
                binding.viewPager.currentItem = 2
            }
        }.launchIn(lifecycleScope)

    }

    private fun addFragment() {

    }
}
package com.naze.todayfeeling.presentation.writing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.naze.todayfeeling.R
import com.naze.todayfeeling.databinding.ActivityWritingBinding
import com.naze.todayfeeling.util.FeelingFragmentAdapter
import com.naze.todayfeeling.util.binding.BindingActivity

class WritingActivity : BindingActivity<ActivityWritingBinding>(R.layout.activity_writing) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        binding.viewPager.adapter = FeelingFragmentAdapter(this)
    }
}
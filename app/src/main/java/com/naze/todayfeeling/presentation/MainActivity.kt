package com.naze.todayfeeling.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.naze.todayfeeling.R
import com.naze.todayfeeling.databinding.ActivityMainBinding
import com.naze.todayfeeling.presentation.home.HomeFragment
import com.naze.todayfeeling.util.binding.BindingActivity
import com.naze.todayfeeling.util.extension.makeToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    private var clickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        initLayout()
        initNavigation()
    }

    private fun initLayout() {

    }

    private fun initNavigation() {
        navController = Navigation.findNavController(this,R.id.main_nav_host_fragment)
        setupWithNavController(binding.bottomNavigationView,navController)
    }

    override fun onBackPressed() {
        Log.d("Fragment Test", "1 ${supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) is HomeFragment}" )
        if (supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) is HomeFragment) { //현재 화면이 홈일 때
            Log.d("Fragment Test", "2 ${supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment)}" )
            val current = System.currentTimeMillis()
            if (current - clickTime >= 2500) {
                clickTime = current
                makeToast("한번 더 클릭 시 종료됩니다.")
            } else {
                finish()
            }
        } else {
            super.onBackPressed()
        }
    }
}
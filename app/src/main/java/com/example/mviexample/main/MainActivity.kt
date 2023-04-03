package com.example.mviexample.main

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.mviexample.R
import com.example.mviexample.Semin
import com.example.mviexample.base.BaseActivity
import com.example.mviexample.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity(override val layoutId: Int = R.layout.activity_main) : BaseActivity<ActivityMainBinding, MainState>() {
    private val viewModel = MainViewModel()
    override fun initView() {
        binding.run {
            vm = viewModel
        }
    }

    override fun initObserver() {
        lifecycleScope.launch(Dispatchers.Main) {
            binding.run {
                viewModel.state.collect { state ->
                    Semin.messageLog("$state")
                    updateView(state)
                }
            }
        }
    }

    override fun updateView(state: MainState) {
        binding.run {
            when(state.event) {
                MainEvent.Init,
                MainEvent.Increment,
                MainEvent.Decrement -> {
                    pbLoading.visibility = View.GONE
                    tvTest.text = state.count.toString()
                }
                MainEvent.Loading -> {
                    pbLoading.visibility = View.VISIBLE
                }
                MainEvent.Error -> {

                }
            }
        }
    }
}
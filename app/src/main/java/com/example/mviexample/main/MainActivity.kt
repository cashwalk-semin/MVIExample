package com.example.mviexample.main

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.mviexample.R
import com.example.mviexample.Semin
import com.example.mviexample.base.BaseActivity
import com.example.mviexample.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding, MainState>(layoutId = R.layout.activity_main) {
    private val viewModel = MainViewModel()

    override fun initView() {
        bind {
            vm = viewModel

            semin.btnError.setOnClickListener {
                viewModel.onInitEvent()
            }
        }
    }

    override fun initCollect() {
        bind {
            lifecycleScope.launch(Dispatchers.Main) {
                viewModel.state.collect { state ->
                    Semin.messageLog("$state")
                    updateView(state)
                }
            }
        }
    }

    override fun updateView(state: MainState) {
        bind {
            when(state.event) {
                MainEvent.Init,
                MainEvent.Normal,
                MainEvent.Increment,
                MainEvent.Decrement -> {
                    semin.root.visibility = View.GONE
                    pbLoading.visibility = View.GONE
                    tvTest.text = state.count.toString()
                }
                MainEvent.Loading -> {
                    semin.root.visibility = View.GONE
                    pbLoading.visibility = View.VISIBLE
                }
                MainEvent.Error -> {
                    semin.root.visibility = View.VISIBLE
                }
            }
        }
    }
}
package com.example.mviexample.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<Binding: ViewDataBinding, State>: AppCompatActivity() {
    abstract val layoutId: Int

    private var _binding: Binding? = null
    val binding: Binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId)
        initView()
        initObserver()
    }

    abstract fun initView()
    abstract fun initObserver()
    protected open fun updateView(state: State) {}
}
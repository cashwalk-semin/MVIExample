package com.example.mviexample.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<Binding: ViewDataBinding, State>(@LayoutRes val layoutId: Int): AppCompatActivity() {

    private var _binding: Binding? = null
    private val binding: Binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId)
        initView()
        initCollect()
    }

    protected fun bind(lambda: Binding.() -> Unit) {
        lambda(binding)
    }

    abstract fun initView()
    abstract fun initCollect()
    protected open fun updateView(state: State) {}
}
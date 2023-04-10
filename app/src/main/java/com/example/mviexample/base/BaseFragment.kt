package com.example.mviexample.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<Binding: ViewDataBinding, State>(@LayoutRes val layoutId: Int): Fragment() {
    private var _binding: Binding? = null
    private val binding: Binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
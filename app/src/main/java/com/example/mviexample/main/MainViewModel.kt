package com.example.mviexample.main

import androidx.lifecycle.viewModelScope
import com.example.mviexample.AppConstants
import com.example.mviexample.base.BaseViewModel
import com.example.mviexample.util.channelToStateFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: BaseViewModel<MainState, MainEvent>() {

    override var currentEvent: MainEvent = MainEvent.Init
    override val events = Channel<MainEvent>()

    override val state = events.channelToStateFlow(MainState(), ::reduceState, viewModelScope)

    override suspend fun onEvent(event: MainEvent) {
        currentEvent = event
        events.send(event)
    }

    override fun reduceState(current: MainState, event: MainEvent): MainState {
        return when (event) {
            is MainEvent.Init -> current.copy(event = event)
            is MainEvent.Normal -> current.copy(event = event)
            is MainEvent.Loading -> current.copy(event = event)
            is MainEvent.Increment -> current.copy(count = current.count.plus(1), event = event)
            is MainEvent.Decrement -> current.copy(count = current.count.minus(1), event = event)
            is MainEvent.Error -> current.copy(event = event)
        }
    }

    fun onIncrementEvent() {
        if (currentEvent == MainEvent.Loading) return

        viewModelScope.launch {
            onEvent(MainEvent.Loading)
            apiTest(true,
                onSuccess = {
                    viewModelScope.launch {
                        onEvent(MainEvent.Increment)
                    }
                },
                onError = {

                })
        }
    }

    fun onDecrementEvent() {
        if (currentEvent == MainEvent.Loading) return

        viewModelScope.launch {
            onEvent(MainEvent.Loading)
            apiTest(true,
                onSuccess = {
                    viewModelScope.launch {
                        onEvent(MainEvent.Decrement)
                    }
                },
                onError = {

                })
        }
    }

    fun onErrorEvent() {
        viewModelScope.launch {
            onEvent(MainEvent.Error)
        }
    }

    fun onNormalEvent() {
        viewModelScope.launch {
            onEvent(MainEvent.Normal)
        }
    }

    private suspend fun apiTest(testFlag: Boolean, onSuccess: () -> Unit, onError: () -> Unit) {
        delay(AppConstants.DELAY)
        if(testFlag) {
            onSuccess()
        }
        else {
            onError()
        }
    }
}

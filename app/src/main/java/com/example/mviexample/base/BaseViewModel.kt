package com.example.mviexample.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State, Event>: ViewModel() {
    protected abstract var currentEvent: Event
    protected abstract val events: Channel<Event>
    abstract val state: StateFlow<State>

    abstract suspend fun onEvent(event: Event)
    protected abstract fun reduceState(current: State, event: Event): State
}
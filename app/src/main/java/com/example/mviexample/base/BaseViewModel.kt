package com.example.mviexample.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State, Event>: ViewModel() {
    protected abstract var currentEvent: Event
    protected abstract val events: Channel<Event>
    abstract val state: StateFlow<State>

    protected suspend fun onEvent(event: Event) {
        currentEvent = event
        events.send(event)
    }

    protected abstract fun reduceState(current: State, event: Event): State
}
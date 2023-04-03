package com.example.mviexample.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*


fun <Event, State> Channel<Event>.channelToStateFlow(state: State, lambda: (State, Event) -> State, scope: CoroutineScope): StateFlow<State> {
    return receiveAsFlow().runningFold(state, lambda).stateIn(scope, SharingStarted.Eagerly, state)
}
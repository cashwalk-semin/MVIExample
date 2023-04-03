package com.example.mviexample.main

sealed class MainEvent {
    object Init : MainEvent()
    object Loading : MainEvent()
    object Increment : MainEvent()
    object Decrement : MainEvent()
    object Error : MainEvent()
}
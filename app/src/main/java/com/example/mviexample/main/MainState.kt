package com.example.mviexample.main

data class MainState(
    val event: MainEvent = MainEvent.Init,
    val count: Int = 0
)